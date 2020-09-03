package com.test.yunmusicapp.model.user

import com.test.yunmusicapp.model.BaseModel

/**
@author by zhulei
@time 2020/8/28 16:51
@description
 */
data class User(var ecode: Int,
                var emsg: String = "",
                var data: UserContent = UserContent()): BaseModel() {
}