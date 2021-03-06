package com.margin.wine.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.margin.wine.local.room.dao.WineDao
import com.margin.wine.local.room.dao.WineNoteDao
import com.margin.wine.local.room.entity.WineEntity
import com.margin.wine.local.room.entity.WineNoteEntity

@Database(
    entities = [
        WineEntity::class,
        WineNoteEntity::class
    ],
    version = 5,
    exportSchema = false
)
abstract class AppDataBase : RoomDatabase() {

    abstract fun wineDao(): WineDao

    abstract fun wineNoteDao(): WineNoteDao

    companion object {
        fun buildDatabase(context: Context) =
            Room.databaseBuilder(context, AppDataBase::class.java, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build()
    }
}