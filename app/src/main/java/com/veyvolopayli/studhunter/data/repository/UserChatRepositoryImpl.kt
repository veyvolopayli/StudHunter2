package com.veyvolopayli.studhunter.data.repository

import android.content.SharedPreferences
import com.veyvolopayli.studhunter.common.Constants
import com.veyvolopayli.studhunter.common.ErrorType
import com.veyvolopayli.studhunter.common.Resource
import com.veyvolopayli.studhunter.data.remote.dto.MessageDTO
import com.veyvolopayli.studhunter.domain.model.Message
import com.veyvolopayli.studhunter.domain.repository.UserChatRepository
import io.ktor.client.HttpClient
import io.ktor.client.plugins.websocket.webSocketSession
import io.ktor.client.request.header
import io.ktor.client.request.url
import io.ktor.websocket.Frame
import io.ktor.websocket.WebSocketSession
import io.ktor.websocket.close
import io.ktor.websocket.readText
import io.ktor.websocket.send
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.isActive
import kotlinx.serialization.json.Json
import java.lang.Exception
import javax.inject.Inject

class UserChatRepositoryImpl @Inject constructor(
    private val client: HttpClient,
    private val prefs: SharedPreferences
) : UserChatRepository {

    private var session: WebSocketSession? = null

    override suspend fun initSessionForNew(pubID: String): Resource<Unit> {
        val token = prefs.getString(Constants.JWT, null) ?: return Resource.Error(ErrorType.Unauthorized())
        return try {
            session = client.webSocketSession {
                header("Authorization", token)
                url("ws://5.181.255.253/chat?pubID=$pubID")
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
                url("ws://5.181.255.253/chat?chatID=$chatID")
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

    override suspend fun sendMessage(message: String) {
        try {
            session?.send(frame = Frame.Text(message))
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override suspend fun disconnect() {
        try {
            session?.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun observeMessages(): Flow<Message> {
        return try {
            session?.incoming?.receiveAsFlow()?.filter { it is Frame.Text }?.map { frame ->
                val jsonString = (frame as? Frame.Text)?.readText() ?: ""
                val messageDTO = Json.decodeFromString<MessageDTO>(jsonString)
                messageDTO.toMessage()
            } ?: flow {  }
        } catch (e: Exception) {
            flow {  }
        }
    }
}