package com.hxt5gh.android.notesapp.Reposetory

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Update
import com.hxt5gh.android.notesapp.database.Notes
import com.hxt5gh.android.notesapp.database.NotesDatabase
import com.hxt5gh.android.notesapp.database.newNotes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class NotesRepository(private val database: NotesDatabase) {



    lateinit var notes : LiveData<List<newNotes>>
    lateinit var notesLowToHigh : LiveData<List<newNotes>>
    lateinit var notesHighToLow : LiveData<List<newNotes>>



  init {
      notes = database.NotesDao().getNotes()
      notesLowToHigh = database.NotesDao().getLowToHigh()
      notesHighToLow = database.NotesDao().getHighToLow()
  }


    suspend fun insertNotes(notes: newNotes)
    {
        database.NotesDao().insertNotes(notes)
    }
    suspend fun updateNotes(notes: newNotes)
    {
        database.NotesDao().updateNotes(notes)
    }

    suspend fun deleteAllNotes()
    {
        database.NotesDao().deleteAllNotes()
    }

    suspend fun deleteNote(userId : Int)
    {
        database.NotesDao().deleteNote(userId)
    }

    suspend fun deleteNoteSecond(notes: newNotes)
    {
        database.NotesDao().deleteNoteSecond(notes)
    }

}


