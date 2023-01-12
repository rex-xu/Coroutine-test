package com.example.coroutine_test

import kotlinx.coroutines.*

class CoroutineTest_2_1_job_cancel {

    private val TAG = ">>>>>>${this@CoroutineTest_2_1_job_cancel.javaClass.simpleName} "

    private val job = Job()
    private val supervisorJob = SupervisorJob()

    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        println("$TAG exception  " + throwable.message)
    }

    private val coroutineScope1 =
        CoroutineScope(job + Dispatchers.Main + exceptionHandler)

    private val coroutineScope2 =
        CoroutineScope(supervisorJob + Dispatchers.Main + exceptionHandler)

    init {

        coroutineScope2.launch {

            val innerJob1 = launch {
                try {
                    println("$TAG innerJob1 a run")
                    delay(3000)
                    println("$TAG innerJob1 b run")
                } catch (e: Exception) {
                    println("$TAG cancel exception 1 $e")
                }
            }
            delay(1000)
            innerJob1.cancel()


            val innerJob2 = launch {
                delay(1000)
                println("$TAG innerJob2 run")
            }
        }


    }

}
