package com.example.coroutine_test

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*

class CoroutineTest_3_1_exception : ViewModel() {

    private val TAG = ">>>>>>${this@CoroutineTest_3_1_exception.javaClass.simpleName} "

    private val supervisorJob = SupervisorJob()
    private val job = Job()

    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        println("$TAG exception " + throwable.message)
    }

    private val coroutineScope =
        CoroutineScope(job + Dispatchers.Main + exceptionHandler)

    init {


        coroutineScope.launch(exceptionHandler) {


            val childJob1 = launch(CoroutineName("child1")) {

                try {
                    delay(1000)
                    println("$TAG child1")
                } catch (e: Exception) {
                    println("$TAG child1 " + e)
                }
            }

            val childJob2 = launch(CoroutineName("child2")) {
                try {
                    delay(1000)
                    println("$TAG child2")
                } catch (e: Exception) {
                    println("$TAG child2 " + e)
                }
            }

            val childJob3 = launch(CoroutineName("child3")) {
                try {
                    delay(1000)
                    println("$TAG child3")
                } catch (e: Exception) {
                    println("$TAG child3 " + e)
                }
            }

            val childJob4 =
                launch(CoroutineName("child4") + CoroutineExceptionHandler { coroutineContext, throwable ->
                    println("$TAG child4 override no use " + throwable)
                }) {

                    throw Exception("throw child4 exception")
                    delay(1000)
                    println("$TAG child4")
                }
            delay(2000)
        }


    }

}