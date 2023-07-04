package com.hxt5gh.android.notesapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.hxt5gh.android.notesapp.Converter


@Database(entities = [newNotes::class], version = 1)
@TypeConverters(Converter::class)
abstract class NotesDatabase : RoomDatabase() {

    abstract fun NotesDao(): NotesDao


    companion object {

        @Volatile
        private var INSTANCE: NotesDatabase? = null

        fun getDataBase(context: Context): NotesDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        context, NotesDatabase::class.java, "notesDB"
                    ).build()

                }

            }
            return INSTANCE!!
        }




    }



}