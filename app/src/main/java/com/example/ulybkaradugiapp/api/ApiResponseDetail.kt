package com.example.ulybkaradugiapp.api

import com.example.ulybkaradugiapp.data.model.DocumentDetail
import com.example.ulybkaradugiapp.data.model.DocumentHeader

data class ApiResponseDetail(
    val data: Data
) {
    data class Data(
        val data1: List<DocumentHeader>,
        val data2: List<DocumentDetail>
    )
}