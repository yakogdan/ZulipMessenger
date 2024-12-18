package com.bogdankostyrko.data.messenger.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "topics")
data class TopicDBO(
    @PrimaryKey
    val id: Int,
    val title: String,
)