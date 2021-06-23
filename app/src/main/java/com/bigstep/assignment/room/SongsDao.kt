package com.bigstep.assignment.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bigstep.assignment.VideoResult

@Dao
interface SongsDao {
    @Query("SELECT * FROM songs where viewed IN (:isViewed)")
    fun getAllSongs(isViewed: Boolean): List<VideoResult>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(users: VideoResult)
}