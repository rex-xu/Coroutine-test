package com.example.coroutine_test

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import java.lang.RuntimeException

class CoroutineTest_3_5_handle_launch_exception : ViewModel() {

    private val TAG =
        ">>>>>>${this@CoroutineTest_3_5_handle_launch_exception.javaClass.simpleName} "

    private val supervisorJob = SupervisorJob()
    private val job = Job()

    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        println("$TAG exception " + throwable.message)
    }

    private val coroutineScope =
        CoroutineScope(job + Dispatchers.Main + exceptionHandler)

    init {

        coroutineScope.launch {

            try {

                println("$TAG run")
                delay(1000)
                throw RuntimeException(" throw exception ")
            } catch (e: Exception) {
                // Handle exception
                println(TAG + "catch inner " + e)
            }

        }


    }

}