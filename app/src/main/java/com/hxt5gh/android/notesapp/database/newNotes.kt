package com.hxt5gh.android.notesapp.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*


@Entity(tableName = "newNotes")
data class newNotes(

    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val title : String,
    val content : String,
    val date: String,
    val priority : String

)
