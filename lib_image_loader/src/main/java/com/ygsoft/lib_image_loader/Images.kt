package com.ygsoft.lib_image_loader

import android.widget.ImageView

/**
@author by zhulei
@time 2020/9/3 15:06
@description
 */
inline fun ImageView.loadView(url: String) = ImageLoaderManager.getInstance().displayImageForView(this, url)