package ru.smak

import ru.smak.net.Client
import ru.smak.ui.ConsoleUI
import kotlin.concurrent.thread

fun main() {
    ConsoleUI().apply{
        thread {
            startReceiving()
        }
    }.also {
        Client(ui = it).start()
    }
}