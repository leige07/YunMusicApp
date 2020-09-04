package com.test.yunmusicapp.api

/**
@author by zhulei
@time 2020/9/3 18:48
@description
 */
data class TestData(
    val email: String,
    val name: String,
    val phone: Phone
)

data class Phone(
    val home: String,
    val mobile: String
)