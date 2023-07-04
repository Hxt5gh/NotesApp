package com.hxt5gh.android.notesapp.ViewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hxt5gh.android.notesapp.Reposetory.NotesRepository

class NotesViewModelFactory(private val repository: NotesRepository , private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NotesViewModel(repository , context) as T
    }
}