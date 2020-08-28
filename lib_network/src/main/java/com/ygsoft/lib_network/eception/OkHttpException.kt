package com.ygsoft.lib_network.eception

/**
@author by zhulei
@time 2020/8/28 15:16
@description
 */
class OkHttpException {
    private val serialVersionUID = 1L

    /**
     * the server return code
     */
    private var ecode = 0

    /**
     * the server return error message
     */
    private var emsg: Any? = null

    constructor(ecode: Int, emsg: Any?) {
        this.ecode = ecode
        this.emsg = emsg
    }

    fun getEcode(): Int {
        return ecode
    }

    fun getEmsg(): Any? {
        return emsg
    }
}