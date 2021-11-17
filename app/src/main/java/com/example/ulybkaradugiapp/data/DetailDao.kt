package com.example.ulybkaradugiapp.data

import androidx.room.*
import com.example.ulybkaradugiapp.data.model.DocumentDetail
import kotlinx.coroutines.flow.Flow

@Dao
interface DetailDao {

    @Query("SELECT * FROM details WHERE id_hd_nakl = :idDocument")
    fun getDetailsByDocument(idDocument: Int): Flow<List<DocumentDetail>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDetail(detail: List<DocumentDetail>)

    @Query("DELETE FROM details WHERE id_hd_nakl = :idDocument")
    suspend fun deleteDetailsByDocument(idDocument: Int)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(detail: DocumentDetail)
}