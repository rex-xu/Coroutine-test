package com.example.coroutine_test

import kotlinx.coroutines.*

class CoroutineTest_1_3_name {

    private val TAG = ">>>>>>${this@CoroutineTest_1_3_name.javaClass.simpleName} "

    private val job1 = Job()

    private val name1 = CoroutineName("coroutine name 1")
    private val name2 = CoroutineName("coroutine name 2")
    private val name3 = CoroutineName("coroutine name 3")


    private val coroutineScope1 =
        CoroutineScope(job1 + Dispatchers.IO + Dispatchers.Main + name2 + name1)

    private val coroutineScope2 =
        CoroutineScope(job1 + Dispatchers.IO + Dispatchers.Main + name1 + name2)

    init {

        coroutineScope1.launch {

            println("$TAG 1 " + coroutineContext[CoroutineName])

        }

        coroutineScope2.launch {

            println("$TAG 2 " + coroutineContext[CoroutineName])

        }

        coroutineScope2.launch(name3) {

            println("$TAG 3 " + coroutineContext[CoroutineName])

        }

    }

}