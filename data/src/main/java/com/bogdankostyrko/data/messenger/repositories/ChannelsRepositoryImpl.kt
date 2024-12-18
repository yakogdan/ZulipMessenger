package com.bogdankostyrko.data.messenger.repositories

import com.bogdankostyrko.data.messenger.database.dao.StreamDao
import com.bogdankostyrko.data.messenger.database.mappers.toDBO
import com.bogdankostyrko.data.messenger.database.mappers.toModels
import com.bogdankostyrko.data.messenger.network.api.ZulipApiService
import com.bogdankostyrko.data.messenger.network.mappers.channels.toModels
import com.bogdankostyrko.messenger.domain.models.StreamModel
import com.bogdankostyrko.messenger.domain.models.TopicModel
import com.bogdankostyrko.messenger.domain.repositories.ChannelsRepository
import javax.inject.Inject

class ChannelsRepositoryImpl @Inject constructor(
    private val zulipApiService: ZulipApiService,
    private val streamDao: StreamDao,
) : ChannelsRepository {

    //region Streams

    override suspend fun getStreamsByQuery(
        query: String,
        isSubscribedStreams: Boolean,
    ): List<StreamModel> {
        return listOf()
    }

    override suspend fun getSubscribedStreamsFromApi(): List<StreamModel> =
        zulipApiService.getSubscribedStreams().subscriptions.toModels().sortedBy { it.id }

    override suspend fun getAllStreamsFromApi(): List<StreamModel> =
        zulipApiService.getAllStreams().streams.toModels().sortedBy { it.id }

    override suspend fun getSubscribedStreamsFromDB(): List<StreamModel> =
        streamDao.getSubscribedStreams().toModels()

    override suspend fun getAllStreamsFromDB(): List<StreamModel> =
        streamDao.getAllStreams().toModels()

    override suspend fun replaceSubscribedStreamsInDB(streams: List<StreamModel>) {
        streamDao.apply {
            deleteSubscribedStreams()
            addStreams(streams.toDBO(isSubscribed = true))
        }
    }

    override suspend fun replaceAllStreamsInDB(streams: List<StreamModel>) {
        streamDao.apply {
            deleteUnsubscribedStreams()
            addUnsubscribedStreams(streams.toDBO(isSubscribed = false))
        }
    }

    //endregion


    //region Topics

    override suspend fun getTopicsInStream(
        streamId: Int,
        streamName: String,
    ): List<TopicModel> =
        zulipApiService.getTopicsInStream(streamId).topics.toModels(parentStreamName = streamName)

    //endregion
}