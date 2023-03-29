package ru.smak.ui

import java.io.BufferedReader
import java.io.InputStreamReader

class ConsoleUI {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private var isActive = true
    fun startReceiving(parse: (String)->Unit){
        isActive = true
        while (isActive){
            parse(br.readLine())
        }
    }

    fun showMessage(msg: String){
        println(msg)
    }

    fun stop(){
        isActive = false
    }
}