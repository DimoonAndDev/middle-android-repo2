package ru.yandex.praktikumchatapp.data

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.retry

class ChatRepository(
    private val api: ChatApi = ChatApi()
) {

    fun getReplyMessage(): Flow<String> {
        var currentDelay = 500L
        return api.getReply().retry { e ->

            if (e != null) {
                delay(currentDelay)
                currentDelay *= DELAY_FACTOR
            }
            true
        }
    }

    companion object {
        const val DELAY_FACTOR = 2
    }
}
