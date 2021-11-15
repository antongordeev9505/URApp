package com.example.ulybkaradugiapp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface DocumentDao {

    @Query("SELECT * FROM documents")
    fun getAllDocuments(): Flow<List<ApiDocument>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDocument(documents: List<ApiDocument>)

    @Query("DELETE FROM documents")
    suspend fun deleteAllDocuments()
}