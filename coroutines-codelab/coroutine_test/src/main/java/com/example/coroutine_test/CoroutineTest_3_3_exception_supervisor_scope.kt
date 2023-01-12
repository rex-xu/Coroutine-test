package com.example.coroutine_test

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*

class CoroutineTest_3_3_exception_supervisor_scope : ViewModel() {

    private val TAG =
        ">>>>>>${this@CoroutineTest_3_3_exception_supervisor_scope.javaClass.simpleName} "

    private val supervisorJob = SupervisorJob()

    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        println("$TAG exception " + throwable.message)
    }

    private val coroutineScope =
        CoroutineScope(supervisorJob + Dispatchers.Main + exceptionHandler)

    init {

        coroutineScope.launch {

            supervisorScope {
                val childJob1 = launch {

                    try {
                        delay(1000)
                        println("$TAG child1")
                    } catch (e: Exception) {
                        println("$TAG child1 " + e)
                    }
                }

                val childJob2 = launch {
                    try {
                        delay(1000)
                        println("$TAG child2")
                    } catch (e: Exception) {
                        println("$TAG child2 " + e)
                    }
                }

                val childJob3 = launch {
                    try {
                        delay(1000)
                        println("$TAG child3")
                    } catch (e: Exception) {
                        println("$TAG child3 " + e)
                    }
                }

                val childJob4 = launch {

                    throw Exception("throw child4 exception")
                    delay(1000)
                    println("$TAG child4")
                }

            }// end of supervisorScope
            delay(2000)
        }


    }

}