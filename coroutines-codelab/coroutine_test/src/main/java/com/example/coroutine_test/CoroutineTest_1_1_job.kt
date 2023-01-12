package com.example.coroutine_test

import kotlinx.coroutines.*

class CoroutineTest_1_1_job {

    private val TAG = ">>>>>>${this@CoroutineTest_1_1_job.javaClass.simpleName} "

    private val job1 = Job()
    private val job2 = Job()
    private val job3 = Job()

    private val coroutineScope1 =
        CoroutineScope(job1 + Dispatchers.Main)

    init {

        println("$TAG 1 $job1")
        println("$TAG 2 $job2")

        val job = coroutineScope1.launch {

            val innerJob = this.coroutineContext[Job]
            println("$TAG inner job 3 $innerJob")

        }
        println("$TAG 4 $job")

        val job2 = coroutineScope1.launch(job2) {

            val innerJob = this.coroutineContext[Job]
            println("$TAG inner job 5 $innerJob")

        }
        println("$TAG 6 $job2")


    }

}