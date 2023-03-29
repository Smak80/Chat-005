package ru.smak.net

import java.net.Socket
import kotlin.concurrent.thread

class Client(host: String = "127.0.0.1", port: Int = 5005) {

    private val s: Socket = Socket(host, port)
    private val chio = ChatIO(s)
    fun start(){
        thread {
            chio.startReceiving(::parse)
        }
    }

    fun sendMessage(msg: String){
        chio.sendMessage(msg)
    }

    private fun parse(msg: String){
        println(msg)
    }
}