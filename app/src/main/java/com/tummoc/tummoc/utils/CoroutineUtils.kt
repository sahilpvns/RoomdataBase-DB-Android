package com.tummoc.tummoc.utils

import android.util.Log
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers

object CoroutineUtils {
    @JvmStatic
    var exceptionHandle = CoroutineExceptionHandler { _, e ->

        Log.e("TAG", "insetUser: " + e.printStackTrace())
    }

    @JvmStatic
    var ioThread = Dispatchers.IO + exceptionHandle
}