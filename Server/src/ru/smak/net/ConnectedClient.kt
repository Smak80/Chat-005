package ru.smak.net

import java.net.Socket
import kotlin.concurrent.thread

class ConnectedClient(val client: Socket) {
    private val chio = ChatIO(client)
    companion object{
        private val clients = mutableListOf<ConnectedClient>()
    }
    init{
        clients.add(this)
    }

    private fun parse(msg: String){
        println(msg)
    }
    fun start(){
        thread {
            chio.startReceiving(::parse)
        }
    }
}
