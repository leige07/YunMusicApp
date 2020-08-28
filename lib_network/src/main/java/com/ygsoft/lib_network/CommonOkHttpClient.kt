package com.ygsoft.lib_network

import com.ygsoft.lib_network.cookie.SimpleCookieJar
import com.ygsoft.lib_network.https.HttpsUtils
import com.ygsoft.lib_network.listener.DisposeDataHandle
import com.ygsoft.lib_network.response.CommonFileCallback
import com.ygsoft.lib_network.response.CommonJsonCallback
import okhttp3.*
import java.io.IOException
import java.util.concurrent.TimeUnit
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLSession

/**
@author by zhulei
@time 2020/8/28 16:23
@description 用来发送get, post请求的工具类，包括设置一些请求的共用参数
 */
class CommonOkHttpClient {
    companion object {
        const val TIME_OUT = 30
        var mOkHttpClient: OkHttpClient? = null

        fun getOkHttpClient(): OkHttpClient? {
            return mOkHttpClient
        }

        /**
         * 通过构造好的Request,Callback去发送请求
         */
        operator fun get(request: Request, handle: DisposeDataHandle): Call {
            val call = mOkHttpClient!!.newCall(request)
            call.enqueue(CommonJsonCallback(handle))
            return call
        }

        fun post(request: Request, handle: DisposeDataHandle): Call {
            val call = mOkHttpClient!!.newCall(request)
            call.enqueue(CommonJsonCallback(handle))
            return call
        }

        fun downloadFile(request: Request, handle: DisposeDataHandle): Call {
            val call = mOkHttpClient!!.newCall(request)
            call.enqueue(CommonFileCallback(handle))
            return call
        }
    }



    init {
        var okHttpClientBuilder = OkHttpClient.Builder()
        okHttpClientBuilder.hostnameVerifier { _, _ -> true }

        /**
         * 为所有请求添加请求头，看个人需求
         */
        okHttpClientBuilder.addInterceptor(object : Interceptor {
            @Throws(IOException::class)
            override fun intercept(chain: Interceptor.Chain): Response {
                val request = chain.request().newBuilder()
                    .addHeader("User-Agent", "yun-Mobile") // 标明发送本次请求的客户端
                    .build()
                return chain.proceed(request)
            }
        })
        okHttpClientBuilder.cookieJar(SimpleCookieJar())
        okHttpClientBuilder.connectTimeout(TIME_OUT.toLong(), TimeUnit.SECONDS)
        okHttpClientBuilder.readTimeout(TIME_OUT.toLong(), TimeUnit.SECONDS)
        okHttpClientBuilder.writeTimeout(TIME_OUT.toLong(), TimeUnit.SECONDS)
        okHttpClientBuilder.followRedirects(true)
        /**
         * trust all the https point
         */
        /**
         * trust all the https point
         */
        okHttpClientBuilder.sslSocketFactory(
            HttpsUtils.initSSLSocketFactory(),
        HttpsUtils.initTrustManager())
        mOkHttpClient = okHttpClientBuilder.build()
    }

}