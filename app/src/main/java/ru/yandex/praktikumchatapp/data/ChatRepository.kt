package ru.yandex.praktikumchatapp.data

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.retry

class ChatRepository(
    private val api: ChatApi = ChatApi()
) {

    fun getReplyMessage() = api.getReply()
        .retry { e ->
            var currentDelay = 10L
            if (e != null) {
                delay(currentDelay)
                currentDelay *= DELAY_FACTOR
            }
            true
        }
    companion object{
        const val DELAY_FACTOR = 2
    }
}
