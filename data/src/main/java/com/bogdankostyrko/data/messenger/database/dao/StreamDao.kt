package com.bogdankostyrko.data.messenger.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bogdankostyrko.data.messenger.database.models.StreamDBO

@Dao
interface StreamDao {

    @Query("SELECT * FROM streams")
    suspend fun getAllStreams(): List<StreamDBO>

    @Query("SELECT * FROM streams WHERE is_subscribed == 1")
    suspend fun getSubscribedStreams(): List<StreamDBO>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addStreams(streams: List<StreamDBO>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUnsubscribedStreams(streams: List<StreamDBO>)

    @Query("DELETE FROM streams WHERE is_subscribed == 0")
    fun deleteUnsubscribedStreams()

    @Query("DELETE FROM streams WHERE is_subscribed == 1")
    fun deleteSubscribedStreams()
}