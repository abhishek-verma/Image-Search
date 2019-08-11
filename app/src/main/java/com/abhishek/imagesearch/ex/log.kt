package com.abhishek.imagesearch.ex

import android.util.Log

fun Any.log(message: String, level: Char = 'd') {
    val TAG = this::class.java.simpleName
    when (level) {
        'i' -> Log.i(TAG, message)
        'd' -> Log.d(TAG, message)
        'e' -> Log.e(TAG, message)
    }
}

