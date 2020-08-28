package com.test.yunmusicapp.model

/**
@author by zhulei
@time 2020/8/28 9:13
@description
 */
enum class CHANNEL(key: String, value: Int) {
    MY("我的", 0x01), DISCORY("发现", 0x02), FRIEND("朋友", 0x03), VIDEO("视频", 0x04);

    private val key: String = key
    private val value: Int = value
    fun getValue(): Int {
        return value
    }

    fun getKey(): String {
        return key
    }

    companion object {
        //所有类型标识
        const val MINE_ID = 0x01
        const val DISCORY_ID = 0x02
        const val FRIEND_ID = 0x03
        const val VIDEO_ID = 0x04
    }

}
