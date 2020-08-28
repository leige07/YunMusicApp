package com.ygsoft.lib_network.request

import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import java.io.File

/**
@author by zhulei
@time 2020/8/28 14:10
@description 对外提供get post 文件上传请求
 */
class CommonRequest {

    companion object {

        fun createPostRequest(url: String, params: RequestParams?): Request {
            return createPostRequest(url, params, null)
        }

        /**
         * 对外创建post请求对象
         * */
        fun createPostRequest(url: String, params: RequestParams?, headers: RequestParams?): Request {
            val mFormBodyBuild = FormBody.Builder()
            if (params != null) {
                for ((key, value) in params.urlParams.entries) {
                    mFormBodyBuild.add(key!!, value!!)
                }
            }
            //添加请求头
            val mHeaderBuild = Headers.Builder()
            if (headers != null) {
                for ((key, value) in headers.urlParams.entries) {
                    mHeaderBuild.add(key!!, value!!)
                }
            }

            val mFormBody = mFormBodyBuild.build()
            val mHeader = mHeaderBuild.build()
            return Request.Builder().url(url).post(mFormBody).headers(mHeader).build()
        }


        fun createGetRequest(url: String, params: RequestParams): Request {
            return createGetRequest(url, params)
        }

        /**
         * 可以带请求头的Get请求
         * */
        fun createGetRequest(url: String, params: RequestParams?, headers: RequestParams?): Request {
            val urlBuilder = StringBuilder(url).append("?")
            if (params != null) {
                for ((key, value) in params.urlParams.entries) {
                    urlBuilder.append(key).append("=").append(value).append("&")
                }
            }
            //添加请求头
            val mHeaderBuild = Headers.Builder()
            if (headers != null) {
                for ((key, value) in headers.urlParams.entries) {
                    mHeaderBuild.add(key!!, value!!)
                }
            }
            val mHeader = mHeaderBuild.build()
            return Request.Builder().url(urlBuilder.substring(0, urlBuilder.length - 1))
                .get()
                .headers(mHeader)
                .build()
        }

        private var FILE_TYPE: MediaType? = "application/octet-stream".toMediaTypeOrNull()
        /**
         * 文件上传方法
         * */
        fun createMultiPostRequest(url: String, params: RequestParams?): Request {
            val requestBody: MultipartBody.Builder = MultipartBody.Builder()
            requestBody.setType(MultipartBody.FORM)
            if (params != null) {
                for ((key, value) in params.fileParams.entries) {
                    if (value is File) {
                        requestBody.addPart(
                            Headers.headersOf("Content-Disposition", "form-data; name=\"$key\""),
                            RequestBody.create(FILE_TYPE, value)
                        )
                    } else if (value is String) {
                        requestBody.addPart(
                            Headers.headersOf("Content-Disposition", "form-data; name=\"$key\""),
                            RequestBody.create(null, value)
                        )
                    }
                }
            }
            return Request.Builder().url(url).post(requestBody.build()).build()
        }
    }

}