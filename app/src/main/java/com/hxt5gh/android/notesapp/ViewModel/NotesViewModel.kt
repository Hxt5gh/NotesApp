package com.hxt5gh.android.notesapp.ViewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.hxt5gh.android.notesapp.Reposetory.NotesRepository
import com.hxt5gh.android.notesapp.database.Notes
import com.hxt5gh.android.notesapp.database.newNotes
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.List

class NotesViewModel(private val repository: NotesRepository , private val context: Context) : ViewModel() {


    val notes : LiveData<List<newNotes>>
    get() =   repository.notes

    val notesLowToHigh : LiveData<List<newNotes>>
    get() = repository.notesLowToHigh

    val notesHighToLow : LiveData<List<newNotes>>
    get() = repository.notesHighToLow

     fun insertNotes(notes: newNotes)
    {
        GlobalScope.launch {
        repository.insertNotes(notes)
        }
    }
     fun updateNotes(notes: newNotes)
    {
        GlobalScope.launch {
            repository.updateNotes(notes)
        }
    }

     fun deleteAllNotes()
    {
        GlobalScope.launch {
            repository.deleteAllNotes()
        }
    }

     fun deleteNote(userId : Int)
    {
        GlobalScope.launch {
            repository.deleteNote(userId)
        }
    }

    fun deleteNoteSecond(notes: newNotes)
    {
        GlobalScope.launch {
        repository.deleteNoteSecond(notes)
        }
    }

}


