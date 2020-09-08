package com.ygsoft.lib_network

import com.ygsoft.lib_network.listener.DisposeDataHandle
import com.ygsoft.lib_network.response.CommonJsonCallback
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Request

/**
@author by zhulei
@time 2020/9/7 12:32
@description
 */
class YgHttpClient {
    private var client: CommonOkHttpClient? = null


    init {
        client = CommonOkHttpClient()
    }

    companion object {

        private var ygHttpClient = YgHttpClient()

        fun getInstance(): YgHttpClient {
            return ygHttpClient
        }


    }

    fun<T> getRequest(request: Request, handle: DisposeDataHandle<T>): Call {
        val call = CommonOkHttpClient.getOkHttpClient()!!.newCall(request)
        call.enqueue(CommonJsonCallback(handle))
        return call
    }

    fun getRequest(request: Request, callback: Callback): Call {
        val call = CommonOkHttpClient.getOkHttpClient()!!.newCall(request)
        call.enqueue(callback)
        return call
    }


}