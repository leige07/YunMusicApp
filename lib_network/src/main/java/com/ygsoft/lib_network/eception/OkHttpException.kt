package com.ygsoft.lib_network.eception

/**
@author by zhulei
@time 2020/8/28 15:16
@description
 */
class OkHttpException {

    /**
     * the server return code
     */
    private var code = 0

    /**
     * the server return error message
     */
    private var msg: String? = null

    constructor(code: Int, msg: String?) {
        this.code = code
        this.msg = msg
    }

    fun getCode(): Int {
        return code
    }

    fun getMsg(): Any? {
        return msg
    }
}