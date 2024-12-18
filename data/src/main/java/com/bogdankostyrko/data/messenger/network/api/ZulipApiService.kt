package com.bogdankostyrko.data.messenger.network.api

import com.bogdankostyrko.data.messenger.network.dto.ResponseToActionDTO
import com.bogdankostyrko.data.messenger.network.dto.allstreams.AllStreamsRootDTO
import com.bogdankostyrko.data.messenger.network.dto.allusers.AllUsersRootDTO
import com.bogdankostyrko.data.messenger.network.dto.events.RegisteredEventQueueDTO
import com.bogdankostyrko.data.messenger.network.dto.events.message.MessageEventsDTO
import com.bogdankostyrko.data.messenger.network.dto.events.reaction.ReactionEventsDTO
import com.bogdankostyrko.data.messenger.network.dto.messsages.MessagesRootDTO
import com.bogdankostyrko.data.messenger.network.dto.singleuser.SingleUserRootDTO
import com.bogdankostyrko.data.messenger.network.dto.subscribedstreams.SubscribedStreamsRootDTO
import com.bogdankostyrko.data.messenger.network.dto.topics.TopicsRootDTO
import com.bogdankostyrko.data.messenger.network.dto.userspresence.AllUsersPresence
import com.bogdankostyrko.data.messenger.network.dto.userspresence.SingleUserPresence
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.create
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

/**
 * Zulip API documentation [here](https://zulip.com/api/)
 */
interface ZulipApiService {

    //region Users

    /**
     * API details [here](https://zulip.com/api/get-users)
     */
    @GET("users")
    suspend fun getAllUsers(): AllUsersRootDTO

    /**
     * API details [here](https://zulip.com/api/get-user)
     */
    @GET("users/{user_id}")
    suspend fun getUserById(
        @Path("user_id") userId: Int,
    ): SingleUserRootDTO

    /**
     * API details [here](https://zulip.com/api/get-presence)
     */
    @GET("realm/presence")
    suspend fun getAllUsersPresence(): AllUsersPresence

    /**
     * API details [here](https://zulip.com/api/get-user-presence)
     */
    @GET(PRESENCE_STATUS)
    suspend fun getUserPresence(
        @Path("user_id_or_email") userIdOrEmail: String,
    ): SingleUserPresence


    /**
     * API details [here](https://zulip.com/api/update-presence)
     */
    @POST("users/me/presence")
    suspend fun updateYourPresence(
        @Query("status") status: String,
        @Query("client") client: String = MOBILE_CLIENT,
    )

    //endregion


    //region Streams

    /**
     * API details [here](https://zulip.com/api/get-subscriptions)
     */
    @GET("users/me/subscriptions")
    suspend fun getSubscribedStreams(): SubscribedStreamsRootDTO

    /**
     * API details [here](https://zulip.com/api/get-streams)
     */
    @GET("streams")
    suspend fun getAllStreams(): AllStreamsRootDTO

    //endregion


    //region Topics

    /**
     * API details [here](https://zulip.com/api/get-stream-topics#get-topics-in-a-stream)
     */
    @GET("users/me/{stream_id}/topics")
    suspend fun getTopicsInStream(
        @Path("stream_id") streamId: Int
    ): TopicsRootDTO

    //endregion


    //region Messages

    /**
     * API details [here](https://zulip.com/api/get-messages#get-messages)
     */
    @GET("messages")
    suspend fun getMessages(
        @Query("num_before") numBefore: Int,
        @Query("num_after") numAfter: Int,
        @Query("anchor") anchor: Any,
        @Query("narrow") narrow: String,
        @Query("apply_markdown") applyMarkdown: Boolean = false,
    ): MessagesRootDTO

    /**
     * API details [here](https://zulip.com/api/send-message)
     */
    @POST("messages")
    suspend fun sendMessage(
        @Query("type") type: String = "stream",
        @Query("content") messageText: String,
        @Query("to") streamName: String,
        @Query("topic") topicName: String,
    )

    /**
     * API details [here](https://zulip.com/api/add-reaction)
     */
    @POST(ADD_OR_REMOVE_REACTION)
    suspend fun addReactionToMessage(
        @Path("message_id") messageId: Int,
        @Query("emoji_name") emojiName: String,
    ): ResponseToActionDTO

    /**
     * API details [here](https://zulip.com/api/remove-reaction)
     */
    @DELETE(ADD_OR_REMOVE_REACTION)
    suspend fun removeReactionToMessage(
        @Path("message_id") messageId: Int,
        @Query("emoji_name") emojiName: String,
    ): ResponseToActionDTO

    //endregion


    //region Events

    /**
     * API details [here](https://zulip.com/api/register-queue)
     */
    @POST("register")
    suspend fun registerEventQueue(@QueryMap queryMap: Map<String, String>): RegisteredEventQueueDTO

    /**
     * API details [here](https://zulip.com/api/delete-queue)
     */
    @DELETE("events")
    suspend fun deleteEventQueue(@Query("queue_id") queueId: String)

    /**
     * API details [here](https://zulip.com/api/get-events)
     */
    @GET("events")
    suspend fun getMessageEvents(
        @Query("queue_id") queueId: String,
        @Query("last_event_id") lastEventId: Int,
    ): MessageEventsDTO

    /**
     * API details [here](https://zulip.com/api/get-events)
     */
    @GET("events")
    suspend fun getReactionEvents(
        @Query("queue_id") queueId: String,
        @Query("last_event_id") lastEventId: Int,
    ): ReactionEventsDTO

    //endregion
}

fun ZulipApiService(
    baseUrl: String,
    username: String,
    apiKey: String,
    json: Json = Json { ignoreUnknownKeys = true },
    okHttpClient: OkHttpClient? = null,
): ZulipApiService {

    val retrofit = retrofit(
        baseUrl = baseUrl,
        username = username,
        apiKey = apiKey,
        json = json,
        okHttpClient = okHttpClient,
    )

    return retrofit.create()
}

private fun retrofit(
    baseUrl: String,
    username: String,
    apiKey: String,
    json: Json,
    okHttpClient: OkHttpClient?,
): Retrofit {

//    val logging = HttpLoggingInterceptor()
//    logging.level = HttpLoggingInterceptor.Level.BODY

    val logging = SimpleLoggingInterceptor()

    val modifiedOkHttpClient = (okHttpClient?.newBuilder() ?: OkHttpClient.Builder())
        .addInterceptor(ZulipApiKeyInterceptor(username = username, apiKey = apiKey))
        .addInterceptor(logging)
        .build()

    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .client(modifiedOkHttpClient)
        .build()
}