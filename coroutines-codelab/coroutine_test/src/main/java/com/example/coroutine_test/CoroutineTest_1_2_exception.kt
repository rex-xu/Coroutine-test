package com.example.coroutine_test

import kotlinx.coroutines.*
import java.lang.RuntimeException

class CoroutineTest_1_2_exception {

    private val TAG = ">>>>>>${this@CoroutineTest_1_2_exception.javaClass.simpleName} "

    private val supervisorJob = SupervisorJob()
    private val job2 = Job()

    private val exceptionHandler1 = CoroutineExceptionHandler { coroutineContext, throwable ->
        println("$TAG exception 1 " + throwable.message)
    }
    private val exceptionHandler2 = CoroutineExceptionHandler { coroutineContext, throwable ->
        println("$TAG exception 2 " + throwable.message)
    }
    private val exceptionHandler3 = CoroutineExceptionHandler { coroutineContext, throwable ->
        println("$TAG exception 3 " + throwable.message)
    }

    private val coroutineScope1 =
        CoroutineScope(supervisorJob + Dispatchers.Main + exceptionHandler1 + exceptionHandler2)

    private val coroutineScope2 =
        CoroutineScope(supervisorJob + Dispatchers.Main + exceptionHandler2 + exceptionHandler1)

    private val coroutineScope1_ =
        CoroutineScope(job2 + Dispatchers.Main + exceptionHandler1 + exceptionHandler2)

    private val coroutineScope2_ =
        CoroutineScope(job2 + Dispatchers.Main + exceptionHandler2 + exceptionHandler1)

    init {

        coroutineScope1.launch {

            throw RuntimeException("custom exception 1")

        }

        coroutineScope2.launch {

            throw RuntimeException("custom exception 2")

        }

        coroutineScope1.launch(exceptionHandler3) {

            throw RuntimeException("custom exception 3")

        }

        coroutineScope1_.launch {

            throw RuntimeException("custom exception 1_")

        }

        coroutineScope2_.launch {

            throw RuntimeException("custom exception 2_")

        }

        coroutineScope1_.launch(exceptionHandler3) {

            throw RuntimeException("custom exception 3_")

        }

    }

}