package com.hxt5gh.android.notesapp.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "Notes")
data class Notes(

    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val title : String,
    val content : String,
    val date: Date,
    val priority : String

)
