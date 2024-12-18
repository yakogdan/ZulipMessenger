package com.bogdankostyrko.data.messenger.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "messages")
data class MessageDBO(
    @PrimaryKey
    val id: Int,
    val avatarResId: Int,
    val userName: String,
    val text: String,
    val date: String,
)