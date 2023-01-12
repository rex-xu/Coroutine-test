package com.example.coroutine_test

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import java.lang.RuntimeException

class CoroutineTest_3_4_handle_async_exception : ViewModel() {

    private val TAG =
        ">>>>>>${this@CoroutineTest_3_4_handle_async_exception.javaClass.simpleName} "

    private val supervisorJob = SupervisorJob()
    private val job = Job()

    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        println("$TAG exception " + throwable.message)
    }

    private val coroutineScope =
        CoroutineScope(job + Dispatchers.Main + exceptionHandler)

    init {

        coroutineScope.launch {

            val deferred = async {


                println("$TAG run")
                delay(1000)
                throw RuntimeException(" throw exception ")
//
//                } catch (e: Exception) {
//                    // Handle exception
//                    println(TAG + "catch inner 1 " + e)
//                }
            }
            try {
                deferred.await()
            } catch (e: Exception) {
                // Handle exception
                println(TAG + "catch inner 2 " + e)
            }

        }


    }

}