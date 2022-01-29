package com.example.networking.di

import android.content.Context
import com.example.networking.utils.InternetUtils
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody

class NetworkConnectivityInterceptor(private var context: Context) : Interceptor  {

    override fun intercept(chain: Interceptor.Chain): Response {

        return if (InternetUtils.isInternetAvailable(context)) {
            val request = chain.request().newBuilder()
                .addHeader("Content-Type", "application/json")
                .method(chain.request().method, chain.request().body)
                .build()
            chain.proceed(request)
        } else {
            return Response.Builder().run {
                code(1300) //Cualquier codigo de Http para manejarlo internamente
                request(chain.request())
                message("Easy security")
                protocol(Protocol.HTTP_2)
                addHeader("content-type", "application/json")
                body(
                    "{info : { type : \"warning\", title: \"Sin conexión\", message : \"Comprueba tu conexión a internet y vuelve a intentarlo.\" }}".toResponseBody(
                        "application/json; charset=utf-8".toMediaTypeOrNull()
                    )
                )
                build()
            }
        }
    }
}