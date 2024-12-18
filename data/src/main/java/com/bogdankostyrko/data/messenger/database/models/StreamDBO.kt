package com.bogdankostyrko.data.messenger.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "streams")
data class StreamDBO(
    @[PrimaryKey ColumnInfo(name = "id")] val id: Int,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "is_subscribed") val isSubscribed: Boolean,
)