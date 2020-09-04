package com.test.yunmusicapp.model.user

import com.test.yunmusicapp.model.BaseModel

/**
@author by zhulei
@time 2020/8/28 16:59
@description
 */
data class UserContent(var userId: String = "",
                  var photoUrl: String = "",
                  var name: String = "",
                  var tick: String = "",
                  var mobile: String = "",
                  var platform: String = "") : BaseModel()