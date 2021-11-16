package com.example.ulybkaradugiapp.api

import retrofit2.http.GET
import retrofit2.http.Query

interface GetDocumentsApi {

    companion object {
        const val BASE_URL = "http://api-test.tdera.ru/"
        const val login = "l12345678"
        const val password = "p12345678"
    }

    @GET("/api/getdocumentlist")
    suspend fun getListOfDocuments(): ApiResponse

    @GET("/api/getdocument")
    suspend fun getDocument(@Query("id") documentId: Int): ApiResponseDetail
}