package com.ygsoft.lib_network.listener

import com.ygsoft.lib_network.eception.OkHttpException
import okhttp3.OkHttpClient

/**
@author by zhulei
@time 2020/8/28 15:04
@description 业务逻辑真正处理的地方，包括java层和业务层异常
 */
interface DisposeDataListener<T> {


    fun onSuccess(response: T)

    fun onFailure(exception: OkHttpException)

}