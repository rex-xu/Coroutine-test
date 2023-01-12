package com.example.coroutine_test

import kotlinx.coroutines.*

@OptIn(ExperimentalStdlibApi::class)
class CoroutineTest_1_4_dispatch {

    private val TAG = ">>>>>>${this@CoroutineTest_1_4_dispatch.javaClass.simpleName} "

    private val job1 = SupervisorJob()

    private val coroutineScope1 =
        CoroutineScope(job1 + Dispatchers.Main + Dispatchers.IO)

    private val coroutineScope2 =
        CoroutineScope(job1 + Dispatchers.IO + Dispatchers.Main)

    init {

        coroutineScope1.launch {

            delay(1000)
            println("$TAG 1 " + coroutineContext[CoroutineDispatcher])

        }

        coroutineScope2.launch {

            println("$TAG 2 " + coroutineContext[CoroutineDispatcher])

        }

        coroutineScope2.launch(Dispatchers.IO) {

            delay(1000)
            println("$TAG 3 " + coroutineContext[CoroutineDispatcher])

        }

    }

}