package ru.smak.ui

import java.io.BufferedReader
import java.io.InputStreamReader

class ConsoleUI : UI {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private var isActive = true
    fun startReceiving(){
        isActive = true
        while (isActive){
            receiver?.invoke(br.readLine())
        }
    }

    override fun showMessage(msg: String){
        println(msg)
    }

    override fun showComment(cmt: String) {
        println(cmt)
    }

    override var receiver: ((String) -> Unit)? = null

    fun stop(){
        isActive = false
    }
}