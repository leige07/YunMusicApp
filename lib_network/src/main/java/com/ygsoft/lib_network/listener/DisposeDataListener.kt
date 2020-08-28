package com.ygsoft.lib_network.listener

/**
@author by zhulei
@time 2020/8/28 15:04
@description 业务逻辑真正处理的地方，包括java层和业务层异常
 */
interface DisposeDataListener {


    fun onSuccess(responseObj: Any)

    fun onFailure(reason: Any)

}