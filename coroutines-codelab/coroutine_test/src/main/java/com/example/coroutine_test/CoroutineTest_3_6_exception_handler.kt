package com.example.coroutine_test

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import java.lang.RuntimeException

class CoroutineTest_3_6_exception_handler : ViewModel() {

    private val TAG =
        ">>>>>>${this@CoroutineTest_3_6_exception_handler.javaClass.simpleName} "

    private val supervisorJob = SupervisorJob()
    private val job = Job()

    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        println("$TAG exception " + throwable.message)
    }

    private val coroutineScope =
        CoroutineScope(job + Dispatchers.Main)

    init {

        coroutineScope.launch(exceptionHandler) {
            launch {
                throw Exception("Failed coroutine")
            }
        }

//        coroutineScope.launch {
//            launch(exceptionHandler) {
//                throw Exception("Failed coroutine")
//            }
//        }
//
//
//        coroutineScope.launch {
//            launch(exceptionHandler + supervisorJob) {
//                throw Exception("Failed coroutine")
//            }
//        }


    }

}