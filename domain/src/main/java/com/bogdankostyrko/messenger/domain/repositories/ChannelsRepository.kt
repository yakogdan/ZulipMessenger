package com.bogdankostyrko.messenger.domain.repositories

import com.bogdankostyrko.messenger.domain.models.StreamModel
import com.bogdankostyrko.messenger.domain.models.TopicModel

interface ChannelsRepository {

    //region Streams

    suspend fun getStreamsByQuery(
        query: String,
        isSubscribedStreams: Boolean,
    ): List<StreamModel>

    suspend fun getSubscribedStreamsFromApi(): List<StreamModel>

    suspend fun getAllStreamsFromApi(): List<StreamModel>

    suspend fun getSubscribedStreamsFromDB(): List<StreamModel>

    suspend fun getAllStreamsFromDB(): List<StreamModel>

    suspend fun replaceSubscribedStreamsInDB(streams: List<StreamModel>)

    suspend fun replaceAllStreamsInDB(streams: List<StreamModel>)

    //endregion


    //region Topics

    suspend fun getTopicsInStream(
        streamId: Int,
        streamName: String,
    ): List<TopicModel>

    //endregion
}