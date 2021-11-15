package com.example.ulybkaradugiapp.data

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

    fun getDocuments() = networkBoundResource(
        query = {
            documentsDao.getAllDocuments()
        },
        fetch = {
            delay(2000)
            api.getListOfDocuments()
        },
        saveFetchResult = { documents ->
            db.withTransaction {
                documentsDao.deleteAllDocuments()
                documentsDao.insertDocument(documents.data)
            }
        }
    )
}