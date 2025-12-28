package com.example.o4eret.core

object NodeService {

    private var isRunning: Boolean = false

    @Synchronized
    fun startNode(): Boolean {
        return if (!isRunning) {
            isRunning = true
            true
        } else {
            false
        }
    }

    @Synchronized
    fun stopNode(): Boolean {
        return if (isRunning) {
            isRunning = false
            true
        } else {
            false
        }
    }

    fun isNodeRunning(): Boolean = isRunning
}
