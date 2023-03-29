package ru.smak.net

import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.Socket

class ChatIO(socket: Socket) {
    private val br = BufferedReader(InputStreamReader(socket.getInputStream()))
    private val pw = PrintWriter(socket.getOutputStream())
    private var isActive = true
    fun startReceiving(parse: (String)->Unit){
        isActive = true
        while (isActive){
            parse(br.readLine())
        }
    }

    fun sendMessage(msg: String){
        pw.println(msg)
        pw.flush()
    }

    fun stop(){
        isActive = false
    }
}