package com.test.yunmusicapp.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.test.yunmusicapp.R

/**
@author by zhulei
@time 2020/8/28 10:16
@description
 */
class MineFragment: Fragment(){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_mine, container, false)
    }

    companion object {
        fun newInstance(): MineFragment {
            return MineFragment()
        }
    }

}