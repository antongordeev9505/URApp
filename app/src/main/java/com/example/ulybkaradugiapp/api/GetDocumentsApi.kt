package com.example.ulybkaradugiapp.api

import com.example.ulybkaradugiapp.api.ApiResponse
import retrofit2.http.GET

interface GetDocumentsApi {

    companion object {
        const val BASE_URL = "http://api-test.tdera.ru/api/"
    }

    @GET("/getdocumentlist")
    suspend fun getListOfDocuments(): ApiResponse
}