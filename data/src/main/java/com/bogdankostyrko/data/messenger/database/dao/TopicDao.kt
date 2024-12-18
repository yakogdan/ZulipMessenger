package com.bogdankostyrko.data.messenger.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.bogdankostyrko.data.messenger.database.models.TopicDBO
import kotlinx.coroutines.flow.Flow

@Dao
interface TopicDao {

    @Query("SELECT * FROM topics")
    fun getAllTopics(): Flow<List<TopicDBO>>
}