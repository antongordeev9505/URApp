package com.example.ulybkaradugiapp.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ApiDocument::class], version = 1)
abstract class DocumentDatabase : RoomDatabase() {

    abstract fun documentDao(): DocumentDao
}