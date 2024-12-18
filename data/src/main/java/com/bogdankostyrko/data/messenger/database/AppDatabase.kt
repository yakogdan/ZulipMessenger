package com.bogdankostyrko.data.messenger.database

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.bogdankostyrko.data.messenger.database.dao.MessageDao
import com.bogdankostyrko.data.messenger.database.dao.StreamDao
import com.bogdankostyrko.data.messenger.database.dao.TopicDao
import com.bogdankostyrko.data.messenger.database.models.MessageDBO
import com.bogdankostyrko.data.messenger.database.models.StreamDBO
import com.bogdankostyrko.data.messenger.database.models.TopicDBO

@Database(
    entities = [
        MessageDBO::class,
        StreamDBO::class,
        TopicDBO::class,
    ],
    version = 1,
    exportSchema = false,
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun messageDao(): MessageDao
    abstract fun streamDao(): StreamDao
    abstract fun topicDao(): TopicDao

    companion object {

        private const val DB_NAME = "messenger.db"

        fun getInstance(application: Application): AppDatabase =
            Room.databaseBuilder(
                context = application,
                klass = AppDatabase::class.java,
                name = DB_NAME
            ).build()
    }
}