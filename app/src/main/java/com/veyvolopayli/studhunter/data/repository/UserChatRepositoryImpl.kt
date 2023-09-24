package com.veyvolopayli.studhunter.data.repository

import android.content.SharedPreferences
import android.util.Log
import com.veyvolopayli.studhunter.domain.model.chat.DealRequest
import com.veyvolopayli.studhunter.common.Constants
import com.veyvolopayli.studhunter.common.ErrorType
import com.veyvolopayli.studhunter.common.Resource
import com.veyvolopayli.studhunter.data.remote.StudHunterApi
import com.veyvolopayli.studhunter.data.remote.dto.MessageDTO
import com.veyvolopayli.studhunter.domain.model.chat.DataTransfer
import com.veyvolopayli.studhunter.domain.model.chat.IncomingTextFrame
import com.veyvolopayli.studhunter.domain.model.chat.OutgoingMessage
import com.veyvolopayli.studhunter.domain.model.chat.Task
import com.veyvolopayli.studhunter.domain.repository.UserChatRepository
import io.ktor.client.HttpClient
import io.ktor.client.plugins.websocket.webSocketSession
import io.ktor.client.request.header
import io.ktor.client.request.url
import io.ktor.websocket.Frame
import io.ktor.websocket.WebSocketSession
import io.ktor.websocket.close
import io.ktor.websocket.readText
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.isActive
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass
import javax.inject.Inject

class UserChatRepositoryImpl @Inject constructor(
    private val client: HttpClient,
    private val prefs: SharedPreferences,
    private val api: StudHunterApi
) : UserChatRepository {

    private var session: WebSocketSession? = null

    private val json = Json {
        serializersModule = SerializersModule {
            polymorphic(DataTransfer::class) {
                subclass(MessageDTO::class)
                subclass(OutgoingMessage::class)
                subclass(Task::class)
                subclass(DealRequest::class)
            }
        }
    }

    override suspend fun initSessionForNew(pubID: String): Resource<Unit> {
        val token = prefs.getString(Constants.JWT, null) ?: return Resource.Error(ErrorType.Unauthorized())
        return try {
            session = client.webSocketSession {
                header("Authorization", token)
                url("${Constants.WEBSOCKET_BASE_URL}chat?pubID=$pubID")
//                url("ws://5.181.255.253/chat?pubID=$$pubID")
            }
            if (session?.isActive == true) {
                Resource.Success(Unit)
            } else {
                Resource.Error(ErrorType.LocalError())
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(error = ErrorType.NetworkError(e.localizedMessage ?: ""))
        }
    }

    override suspend fun initSession(chatID: String): Resource<Unit> {
        return try {
            val token = prefs.getString(Constants.JWT, null) ?: return Resource.Error(ErrorType.Unauthorized())
            session = client.webSocketSession {
                header("Authorization", token)
                url("${Constants.WEBSOCKET_BASE_URL}chat?chatID=$chatID")
//                url("ws://5.181.255.253/chat?chatID=$chatID")
            }
            if (session?.isActive == true) {
                Resource.Success(Unit)
            } else {
                Resource.Error(error = ErrorType.LocalError())
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(error = ErrorType.NetworkError(e.localizedMessage ?: ""))
        }
    }

    override suspend fun sendMessage(message: OutgoingMessage) {
        try {
            val incomingTextFrame = IncomingTextFrame(type = "message", data = message)
            session?.send(frame = Frame.Text(json.encodeToString(incomingTextFrame)))
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override suspend fun sendOfferRequest(jobDeadline: Long) {
        try {
            val dealRequest = DealRequest(jobDeadline)
            val textFrame = IncomingTextFrame(type = "deal_request", data = dealRequest)
            val textFrameString = json.encodeToString(textFrame)
            session?.send(frame = Frame.Text(textFrameString)) ?: Log.e("VM_SEND_REQ", "SESSION IS NULL")
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override suspend fun sendOfferResponse(task: Task) {
        try {
            val incomingTextFrame = IncomingTextFrame(type = "task", task)
            val incomingTextFrameString = json.encodeToString(incomingTextFrame)
            session?.send(frame = Frame.Text(incomingTextFrameString))
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override suspend fun getMessagesByChatId(token: String, chatId: String): List<MessageDTO> {
        return api.getMessagesByChatId(token = token, chatId = chatId)
    }

    override suspend fun getMessagesByPublicationId(
        token: String,
        pubId: String
    ): List<MessageDTO> {
        return api.getMessagesByPublicationId(token = token, pubId = pubId)
    }

    override suspend fun getTaskByChatId(token: String, chatId: String): Task {
        return api.getTaskByChatId(token = token, chatId = chatId)
    }

    override suspend fun disconnect() {
        try {
            session?.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun observeMessages(): Flow<IncomingTextFrame> {
        return try {
            val flow = session?.incoming?.receiveAsFlow()?.map { frame ->
                Log.e("TAG", (frame as? Frame.Text)?.readText() ?: "")
                json.decodeFromString<IncomingTextFrame>((frame as? Frame.Text)?.readText() ?: "")
            } ?: flow { }
            flow
        } catch (e: Exception) {
            e.printStackTrace()
            flow { }
        }
    }
}