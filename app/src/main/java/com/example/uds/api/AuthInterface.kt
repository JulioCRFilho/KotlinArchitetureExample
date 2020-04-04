package com.example.uds.api

interface AuthInterface {
    fun onStarted()
    fun onSuccess()
    fun onFailure(message: String)
}