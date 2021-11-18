package com.example.ulybkaradugiapp.data

import android.util.Log
import androidx.room.withTransaction
import com.example.ulybkaradugiapp.api.GetDocumentsApi
import com.example.ulybkaradugiapp.data.model.DocumentDetail
import com.example.ulybkaradugiapp.other.networkBoundResource
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetDocumentsRepository @Inject constructor(
    private val api: GetDocumentsApi,
    private val db: DocumentDatabase
) {
    private val documentsDao = db.documentDao()
    private val detailDao = db.detailDao()

    //метод для получения данных и кэширования их
    fun getDocuments(shouldFetch: Boolean) = networkBoundResource(
        query = {
            documentsDao.getAllDocuments()
        },
        fetch = {
            api.getListOfDocuments()
        },
        saveFetchResult = { documents ->
            //удаление и вставка данных совершается одним действием
            db.withTransaction {
                documentsDao.deleteAllDocuments()
                documentsDao.insertDocument(documents.data)
            }
        }, {shouldFetch}
    )

    fun getDetails(idDocument: Int, shouldFetch: Boolean) = networkBoundResource(
        query = {
            detailDao.getDetailsByDocument(idDocument)
        },
        fetch = {
            api.getDocument(idDocument)
        },
        saveFetchResult = { details ->
            db.withTransaction {
                detailDao.deleteDetailsByDocument(idDocument)
                detailDao.insertDetail(details.data.data2)
            }
        },
        shouldFetch = {shouldFetch}
    )

    suspend fun updateDetail(detail: DocumentDetail){
        detailDao.updateIsReadyField(detail)
    }

    private fun shouldUpdateDetails(): Boolean {
        return true
    }

    suspend fun tryUpdateDetails(idDocument: Int) {
        if (shouldUpdateDetails()) fetchDetails(idDocument)
    }

    private suspend fun fetchDetails(idDocument: Int) {
        try {
            val details = api.getDocument(idDocument)
            detailDao.insertDetail(details.data.data2)
        } catch (error: Throwable){
            Log.d("error", "no connection")
        }
    }
}