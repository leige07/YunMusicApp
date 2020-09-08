package com.ygsoft.lib_network.listener

/**
@author by zhulei
@time 2020/8/28 15:10
@description
 */
class DisposeDataHandle<T> {
    var mListener: DisposeDataListener<T>? = null
    var mClass: Class<T>? = null
    var mSource: String? = null //文件下载路径

//    constructor(listener: DisposeDataListener<T>?) {
//        mListener = listener
//    }

    constructor(listener: DisposeDataListener<T>?, clazz: Class<T>?) {
        mListener = listener
        mClass = clazz
    }

    constructor(listener: DisposeDataListener<T>?, source: String?) {
        mListener = listener
        mSource = source
    }
}