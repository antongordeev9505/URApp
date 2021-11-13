package com.example.ulybkaradugiapp.data

import com.example.ulybkaradugiapp.api.GetDocumentsApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetDocumentsRepository @Inject constructor(private val getDocumentsApi: GetDocumentsApi) {
}