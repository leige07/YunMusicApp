package com.test.yunmusicapp.api

import com.test.yunmusicapp.model.user.User
import com.ygsoft.lib_network.CommonOkHttpClient
import com.ygsoft.lib_network.listener.DisposeDataHandle
import com.ygsoft.lib_network.listener.DisposeDataListener
import com.ygsoft.lib_network.request.CommonRequest
import com.ygsoft.lib_network.request.RequestParams
import java.util.*

/**
@author by zhulei
@time 2020/8/28 16:44
@description
 */
class RequestCenter {

    companion object {
        private const val ROOT_URL = "http://imooc.com/api"
        //private static final String ROOT_URL = "http://39.97.122.129";

        //private static final String ROOT_URL = "http://39.97.122.129";
        /**
         * 首页请求接口
         */
        private const val HOME_RECOMMAND = "$ROOT_URL/module_voice/home_recommand"

        private const val HOME_RECOMMAND_MORE = "$ROOT_URL/module_voice/home_recommand_more"

        private const val HOME_FRIEND = "$ROOT_URL/module_voice/home_friend"

        /**
         * 登陆接口
         */
        var LOGIN = "https://www.wanandroid.com/article/list/0/json"


        //根据参数发送所有post请求
        fun getRequest(url: String, params: RequestParams, listener: DisposeDataListener, clazz: Class<*>) {
            CommonOkHttpClient.getRequest(
                CommonRequest.createGetRequest(url, params),
                DisposeDataHandle(listener, clazz)
            )
        }

        /**
         * 用户登陆请求
         */
        fun login(listener: DisposeDataListener) {
            val params = RequestParams()
            params.put("mb", "18734924592")
            params.put("pwd", "999999q")
            getRequest(LOGIN, params, listener, Objects::class.java)
        }
    }

}