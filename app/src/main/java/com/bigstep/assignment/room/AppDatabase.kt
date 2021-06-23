package com.bigstep.assignment.room

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import com.bigstep.assignment.VideoResult

@Database(entities = arrayOf(VideoResult::class),
    version = 5,

)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getSongsDao(): SongsDao
}