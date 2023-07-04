package com.hxt5gh.android.notesapp

import androidx.room.TypeConverter
import java.util.Date

class Converter {


    @TypeConverter
    fun longToDate(value : Long) : Date
    {
        return Date(value)
    }
    @TypeConverter
    fun dateToLong(value : Date) : Long
    {
        return value.time
    }
}