package com.ecirstea.creepyrabbit.core

import com.ecirstea.creepyrabbit.utils.SharedApp
import okhttp3.Interceptor
import okhttp3.Response

class ServiceInterceptor : Interceptor{

    private var token : String = SharedApp.prefs.token;

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()

        if(request.header("No-Authentication")==null){

            if(!token.isNullOrEmpty())
            {
                val finalToken = "Bearer $token"
                request = request.newBuilder()
                    .addHeader("Authorization",finalToken)
                    .build()
            }
        }
        return chain.proceed(request)
    }
}