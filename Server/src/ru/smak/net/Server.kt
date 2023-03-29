package ru.smak.net

import java.net.ServerSocket
import kotlin.concurrent.thread

class Server(port: Int = 5005) {

    private val ss: ServerSocket = ServerSocket(port)
    private var isActive = true

    fun start(){
        isActive = true
        thread {
            while (isActive) {
                ConnectedClient(ss.accept()).start()
            }
        }
    }

    fun stop(){
        isActive = false
        ss.close()
    }
}