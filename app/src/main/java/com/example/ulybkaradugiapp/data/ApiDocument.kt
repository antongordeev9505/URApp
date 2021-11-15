package com.example.ulybkaradugiapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "documents")
data class ApiDocument(
    val id_pos: Int,
    @PrimaryKey val id_record: Int,
    val nom_route: String,
    val nom_zak: String
)