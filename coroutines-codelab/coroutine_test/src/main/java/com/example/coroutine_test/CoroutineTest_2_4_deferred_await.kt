package com.example.coroutine_test

import kotlinx.coroutines.*

class CoroutineTest_2_4_deferred_await {

    private val TAG = ">>>>>>${this@CoroutineTest_2_4_deferred_await.javaClass.simpleName} "

    init {

        runBlocking {
            val startTime = System.currentTimeMillis()
            val deferred = async(Dispatchers.Default) {
                var nextPrintTime = startTime
                var i = 0
                try {
                    while (i < 5 && isActive) {
                        // print a message twice a second
                        if (System.currentTimeMillis() >= nextPrintTime) {
                            println("$TAG Hello ${i++}")
                            nextPrintTime += 500L
                        }
                    }
                } catch (e: Exception) {
                    println("$TAG  " + e)
                } finally {
                    // the coroutine work is completed so we can cleanup
                    withContext(NonCancellable) {
                        delay(3000)
                        println("$TAG Clean up!")
                    }
                }
                i
            }
            delay(10000L)

            println("$TAG Done! " + deferred.join())
            println("$TAG Done! " + deferred.await())
        }

    }

}
