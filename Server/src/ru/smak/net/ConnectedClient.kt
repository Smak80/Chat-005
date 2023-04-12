package ru.smak.net

import java.net.Socket
import kotlin.concurrent.thread

class ConnectedClient(val client: Socket) {
    private val chio = ChatIO(client)
    private var name: String? = null
    companion object{
        private val clients = mutableListOf<ConnectedClient>()
    }
    init{
        clients.add(this)
        send(Command.INTRODUCE, "Введите своё имя:")
    }

    private fun send(cmd: Command, data: String) {
        val d = "${cmd.name}:$data"
        chio.sendMessage(d)
    }

    private fun broadcast(cmd: Command, data: String) {
        clients.forEach{client -> client.name?.let{client.send(cmd, data)}}
    }

    private fun parse(msg: String){
        if (name == null) {
            if (clients.find{it.name == msg} == null) {
                name = msg
                broadcast(Command.MESSAGE, "Пользователь $name зашел в чат!")
            } else {
                send(Command.INTRODUCE, "Имя занято, введите другое")
            }
        }  else {
            broadcast(Command.MESSAGE, "$name: $msg")
        }
    }
    fun start(){
        thread {
            chio.startReceiving(::parse)
        }
    }
}
