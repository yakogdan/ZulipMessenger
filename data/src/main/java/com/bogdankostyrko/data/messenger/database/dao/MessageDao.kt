package com.bogdankostyrko.data.messenger.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.bogdankostyrko.data.messenger.database.models.MessageDBO
import kotlinx.coroutines.flow.Flow

@Dao
interface MessageDao {

    @Query("SELECT * FROM messages")
    fun getAllMessages(): Flow<List<MessageDBO>>
}