package com.example.ulybkaradugiapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.ulybkaradugiapp.data.model.ApiDocument
import com.example.ulybkaradugiapp.data.model.DocumentDetail

@Database(
    entities = [ApiDocument::class,
        DocumentDetail::class], version = 1
)
abstract class DocumentDatabase : RoomDatabase() {

    abstract fun documentDao(): DocumentDao

    abstract fun detailDao(): DetailDao
}