package com.hxt5gh.android.notesapp.database

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface NotesDao {

    @Insert
    suspend fun insertNotes(notes: newNotes)

    @Update
    suspend fun updateNotes(notes: newNotes)

    @Query("DELETE FROM newNotes ")
    suspend fun deleteAllNotes()

    @Query("DELETE FROM newNotes WHERE id = :userId")
    suspend fun deleteNote(userId : Int)

    @Delete
    suspend fun deleteNoteSecond(notes: newNotes)

    @Query("SELECT * FROM  newNotes")
     fun getNotes() : LiveData<List<newNotes>>

    @Query("SELECT * FROM  newNotes ORDER BY priority  ASC")
    fun getLowToHigh() : LiveData<List<newNotes>>

    @Query("SELECT * FROM  newNotes ORDER BY priority DESC")
    fun getHighToLow() : LiveData<List<newNotes>>
}