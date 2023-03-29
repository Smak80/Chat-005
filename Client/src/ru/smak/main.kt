package ru.smak

import ru.smak.net.Client
import ru.smak.ui.ConsoleUI
import kotlin.concurrent.thread

fun main() {
    Client().also {c ->
        c.start()
        thread {
            ConsoleUI().startReceiving {
                c.sendMessage(it)
            }
        }
    }
}