package com.example.ulybkaradugiapp.api

//doesn`t use anywhere yet
data class ApiResponseHead(
    val data1: List<Data1>
) {
    data class Data1(
        val dat_doc: String,
        val id_hd_nakl: Int,
        val name_post: String
    )
}