package com.example.coroutine_test

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.MobileAds
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    private val job1 = SupervisorJob()
    private val job2 = SupervisorJob()
    private val job3 = Job()

    private val name1 = CoroutineName("coroutine name 1")
    private val name2 = CoroutineName("coroutine name 2")

    private val exceptionHandler1 = CoroutineExceptionHandler { coroutineContext, throwable ->
        println(">>>>>> exception 1 " + throwable.message)
    }
    private val exceptionHandler2 = CoroutineExceptionHandler { coroutineContext, throwable ->
        println(">>>>>> exception 2 " + throwable.message)
    }

    private val coroutineScope1 =
        CoroutineScope(job1 + Dispatchers.IO + Dispatchers.Main + name1 + exceptionHandler1 + exceptionHandler2)
    private val coroutineScope2 = CoroutineScope(job1 + Dispatchers.Main + Dispatchers.IO)
    private val uiScope3 = CoroutineScope(job1 + Dispatchers.Main + job2)
    private val uiScope4 = CoroutineScope(job2 + Dispatchers.Main + job1)
    private val uiScope5 = CoroutineScope(job1)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        MobileAds.initialize(this) {}


//        val coroutineTest = CoroutineTest_1_1_job()
//
//        val coroutineTest = CoroutineTest_1_2_exception()
//
//        val coroutineTest = CoroutineTest_1_3_name()
//
//        val coroutineTest = CoroutineTest_1_4_dispatch()
//
//        val coroutineTest = CoroutineTest_2_1_job_cancel()
//
//        val coroutineTest = CoroutineTest_2_2_job_check_cancel_state()
//
//        val coroutineTest = CoroutineTest_2_3_job_cancel_join()
//
//        val coroutineTest = CoroutineTest_2_4_deferred_await()
//
//        val coroutineTest = CoroutineTest_2_5_callbacks(findViewById(R.id.multiple_ad_sizes_view))
//
        val coroutineTest = CoroutineTest_3_1_exception()
//
//        val coroutineTest = CoroutineTest_3_2_exception_supervisor_job()
//
//        val coroutineTest = CoroutineTest_3_3_exception_supervisor_scope()
//
//        val coroutineTest = CoroutineTest_3_4_handle_async_exception()
//        val coroutineTest = CoroutineTest_3_5_handle_launch_exception()
//        val coroutineTest = CoroutineTest_3_6_exception_handler()

    }

}