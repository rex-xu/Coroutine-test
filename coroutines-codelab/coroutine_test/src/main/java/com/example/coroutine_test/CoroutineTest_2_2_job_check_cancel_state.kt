package com.example.coroutine_test

import kotlinx.coroutines.*

class CoroutineTest_2_2_job_check_cancel_state {

    private val TAG = ">>>>>>${this@CoroutineTest_2_2_job_check_cancel_state.javaClass.simpleName} "

    private val singleDispatcher = newSingleThreadContext("")

    init {

        runBlocking {
            val startTime = System.currentTimeMillis()
            val job = launch(Dispatchers.Default) {
                var nextPrintTime = startTime
                var i = 0
                try {
                    while (i < 5
//                        && isActive
                    ) {
//                        yield()
//                        ensureActive()
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
            }
            delay(1000L)
            println("$TAG Cancel!")
            job.cancel()
//            job.join()
            println("$TAG Done!")
            job.join()
        }

    }

}
