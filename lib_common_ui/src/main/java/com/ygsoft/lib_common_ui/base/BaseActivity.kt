package com.ygsoft.lib_common_ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ygsoft.lib_common_ui.utils.StatusBarUtil

/**
@author by zhulei
@time 2020/8/28 10:08
@description
 */
open class BaseActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        StatusBarUtil.statusBarLightMode(this)
    }
}