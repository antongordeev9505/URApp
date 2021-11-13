package com.example.ulybkaradugiapp

import retrofit2.http.GET

interface GetDocumentsApi {

    companion object {
        const val BASE_URL = "http://api-test.tdera.ru/api/"
    }

    @GET("/getdocumentlist")
    suspend fun getListOfDocuments(): ApiResponse
}