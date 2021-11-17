package com.example.ulybkaradugiapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "details")
data class DocumentDetail(
    val good_qty: Double,
    @PrimaryKey val id_good_nakl: Int,
    val id_hd_nakl: Int,
    val pos_category_name: String,
    val pos_group_name: String,
    val pos_name: String
)