package com.example.coroutine_test

import android.content.Context
import android.view.View
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.admanager.AdManagerAdRequest
import com.google.android.gms.ads.admanager.AdManagerAdView
import kotlinx.coroutines.*
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract
import kotlin.coroutines.Continuation
import kotlin.coroutines.intrinsics.intercepted
import kotlin.coroutines.intrinsics.suspendCoroutineUninterceptedOrReturn
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class CoroutineTest_2_5_callbacks(adManagerAdView: AdManagerAdView) {

    private val TAG = ">>>>>>${this@CoroutineTest_2_5_callbacks.javaClass.simpleName} "

    private val job = Job()
    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        println("$TAG exception  " + throwable.message)
    }
    private val customScope = CoroutineScope(job + Dispatchers.IO + exceptionHandler)


    init {

        customScope.launch {
            val adRequest = AdManagerAdRequest.Builder().build()

            val deferred = async(Dispatchers.Main) {
//                coroutineContext[Job]?.cancel()
                val value = adManagerAdView.loadAdByCoroutine(adRequest)
                println("$TAG" + value)
                value
            }
            val a = deferred.await()
        }
    }


     suspend fun AdManagerAdView.loadAdByCoroutine(adRequest: AdManagerAdRequest) =
        suspendCancellableCoroutine { continuation ->

            continuation.invokeOnCancellation {
                println("$TAG do clean!")
                this.destroy()
            }

            adListener = object : AdListener() {
                override fun onAdClicked() {
                    // Code to be executed when the user clicks on an ad.
                }

                override fun onAdClosed() {
                    // Code to be executed when the user is about to return
                    // to the app after tapping on an ad.
                }

                override fun onAdFailedToLoad(adError: LoadAdError) {
                    // Code to be executed when an ad request fails.
                    continuation.resumeWithException(Exception(adError.message))
                }

                override fun onAdImpression() {
                    // Code to be executed when an impression is recorded
                    // for an ad.
                }

                override fun onAdLoaded() {
                    // Code to be executed when an ad finishes loading.
                    continuation.resume(123)
                }

                override fun onAdOpened() {
                    // Code to be executed when an ad opens an overlay that
                    // covers the screen.
                }
            }

            loadAd(adRequest)
        }


}


