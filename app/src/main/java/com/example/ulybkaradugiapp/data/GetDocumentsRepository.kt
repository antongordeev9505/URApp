package com.example.ulybkaradugiapp.data

import android.util.Log
import androidx.room.withTransaction
import com.example.ulybkaradugiapp.api.GetDocumentsApi
import com.example.ulybkaradugiapp.other.networkBoundResource
import kotlinx.coroutines.delay
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
    fun getDocuments() = networkBoundResource(
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
        }
    )

    fun getDetails(idDocument: Int) = networkBoundResource(
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
        }
    )
}