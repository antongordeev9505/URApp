package com.example.ulybkaradugiapp.data

import android.util.Log
import androidx.room.withTransaction
import com.example.ulybkaradugiapp.api.GetDocumentsApi
import com.example.ulybkaradugiapp.api.ApiResponseDetail
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

    //метод для получения данных и кэширования их
    fun getDocuments() = networkBoundResource(
        query = {
            Log.d("proverka", "db")
            documentsDao.getAllDocuments()
        },
        fetch = {
            Log.d("proverka", "fetch")
            delay(2000)
            api.getListOfDocuments()
        },
        saveFetchResult = { documents ->
            //удаление и вставка данных совершается одним действием
            db.withTransaction {
                Log.d("proverka", "save")
                documentsDao.deleteAllDocuments()
                documentsDao.insertDocument(documents.data)
            }
        }
    )

    suspend fun getDetails(): ApiResponseDetail {
        val doc = api.getDocument(115725342)
        return doc
    }
}