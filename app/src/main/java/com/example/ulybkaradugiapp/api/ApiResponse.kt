package com.example.ulybkaradugiapp.api

import com.example.ulybkaradugiapp.data.model.ApiDocument

//модель для получения данных с сервера
data class ApiResponse(
    val data: List<ApiDocument>
)