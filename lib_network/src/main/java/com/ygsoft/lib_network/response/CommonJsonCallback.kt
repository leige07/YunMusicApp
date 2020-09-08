package com.ygsoft.lib_network.response

import android.os.Handler
import android.os.Looper
import com.google.gson.Gson
import com.ygsoft.lib_network.eception.OkHttpException
import com.ygsoft.lib_network.listener.DisposeDataHandle
import com.ygsoft.lib_network.listener.DisposeDataListener
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type


/**
@author by zhulei
@time 2020/8/28 15:02
@description 专门处理Json的回调
 */
open class CommonJsonCallback<T>: Callback {

    companion object {
        /**
         * the logic layer exception, may alter in different app
         */
        const val RESULT_CODE = "ecode" // 有返回则对于http请求来说是成功的，但还有可能是业务逻辑上的错误
        const val RESULT_CODE_VALUE = 0
        const val ERROR_MSG = "emsg"
        const val EMPTY_MSG = ""

        /**
         * the java layer exception, do not same to the logic error
         */
        const val NETWORK_ERROR = -1 // the network relative error
        const val JSON_ERROR = -2 // the JSON relative error
        const val OTHER_ERROR = -3 // the unknow error
    }
//
//    /**
//     * 将其它线程的数据转发到UI线程
//     */
    private var mDeliveryHandler: Handler? = null
    private var mListener: DisposeDataListener<T>? = null
    private var mClass: Class<T>? = null
//
    constructor(mClass: Class<T>, listener: DisposeDataListener<T>) {
        mListener = listener
        this.mClass = mClass
        mDeliveryHandler = Handler(Looper.getMainLooper())
    }

    constructor(handle: DisposeDataHandle<T>) {
        mListener = handle.mListener
        this.mClass = handle.mClass
        mDeliveryHandler = Handler(Looper.getMainLooper())
    }

    override fun onFailure(call: Call, e: IOException) {
        mDeliveryHandler?.post{
            mListener?.onFailure(OkHttpException(NETWORK_ERROR, e.message))
        }
    }

    override fun onResponse(call: Call, response: Response) {
        var result = response.body().string()
        mDeliveryHandler?.post{
            handleResponse(result)
        }
    }

    private fun handleResponse(responseObj: String?) {
        if (responseObj == null || responseObj.toString().trim { it <= ' ' } == "") {
            mListener!!.onFailure(OkHttpException(NETWORK_ERROR, EMPTY_MSG))
            return
        }

        try {
            /**
             * 协议确定后看这里如何修改
             */
                val obj: T = Gson().fromJson(responseObj, mClass)
                if (obj != null) {
                    mListener!!.onSuccess(obj)
                } else {
                    mListener!!.onFailure(OkHttpException(JSON_ERROR, EMPTY_MSG))
                }
        } catch (e: Exception) {
            mListener!!.onFailure(OkHttpException(OTHER_ERROR, e.message))
            e.printStackTrace()
        }
    }

}