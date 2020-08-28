package com.ygsoft.lib_network.response

import android.os.Handler
import android.os.Looper
import android.os.Message
import com.ygsoft.lib_network.eception.OkHttpException
import com.ygsoft.lib_network.listener.DisposeDataHandle
import com.ygsoft.lib_network.listener.DisposeDownloadListener
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream

/**
@author by zhulei
@time 2020/8/28 15:27
@description 处理文件类型的响应
 */
class CommonFileCallback: Callback {

    companion object {

        const val NETWORK_ERROR = -1 // the network relative error
        const val IO_ERROR = -2 // the JSON relative error
        const val EMPTY_MSG = ""

        const val PROGRESS_MESSAGE = 0x01
    }

    /**
     * 将其它线程的数据转发到UI线程
     */
    private var mDeliveryHandler: Handler? = null
    private var mListener: DisposeDownloadListener? = null
    private var mProgress = 0
    private var mFilePath: String? = null

    constructor(handle: DisposeDataHandle) {
        mListener = handle.mListener as DisposeDownloadListener?
        this.mFilePath = handle.mSource
        mDeliveryHandler = object : Handler(Looper.getMainLooper()) {
            override fun handleMessage(msg: Message) {
                when (msg.what) {
                    PROGRESS_MESSAGE -> mListener!!.onProgress(msg.obj as Int)
                }
            }
        }
    }

    override fun onFailure(call: Call, e: IOException) {
        mDeliveryHandler!!.post {
            mListener!!.onFailure(OkHttpException(NETWORK_ERROR, e))
        }
    }

    override fun onResponse(call: Call, response: Response) {
        val file: File? = handleResponse(response)
        mDeliveryHandler!!.post {
            if (file != null) {
                mListener!!.onSuccess(file)
            } else {
                mListener!!.onFailure(OkHttpException(IO_ERROR, EMPTY_MSG))
            }
        }
    }

    private fun handleResponse(response: Response): File? {
        if (response == null) {
            return null
        }

        var inputStream: InputStream? = null
        var file: File? = null
        var fos: FileOutputStream? = null
        val buffer = ByteArray(2048)
        var length: Int
        var currentLength = 0
        val sumLength: Double
        try {
            checkLocalFilePath(mFilePath!!)
            file = File(mFilePath!!)
            fos = FileOutputStream(file!!)
            inputStream = response.body!!.byteStream()
            sumLength = response.body!!.contentLength().toDouble()
            while (inputStream.read(buffer).also { length = it } != -1) {
                fos.write(buffer, 0, length)
                currentLength += length
                mProgress = (currentLength / sumLength * 100).toInt()
                mDeliveryHandler!!.obtainMessage(PROGRESS_MESSAGE, mProgress).sendToTarget()
            }
            fos.flush()
        } catch (e: Exception) {
            file = null
        } finally {
            try {
                fos?.close()
                inputStream?.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        return file!!
    }

    private fun checkLocalFilePath(localFilePath: String) {
        val path = File(localFilePath.substring(0, localFilePath.lastIndexOf("/") + 1))
        val file = File(localFilePath)
        if (!path.exists()) {
            path.mkdirs()
        }
        if (!file.exists()) {
            try {
                file.createNewFile()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

}