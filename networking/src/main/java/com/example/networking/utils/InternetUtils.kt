package com.example.networking.utils

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager

class InternetUtils {
    companion object {

        @SuppressLint("MissingPermission")
        fun isInternetAvailable(context: Context): Boolean {
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            return activeNetworkInfo != null && activeNetworkInfo.isConnected
        }
    }

}