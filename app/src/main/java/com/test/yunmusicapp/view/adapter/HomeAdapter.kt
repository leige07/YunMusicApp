package com.test.yunmusicapp.view.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.test.yunmusicapp.model.CHANNEL
import com.test.yunmusicapp.view.fragment.MineFragment

/**
@author by zhulei
@time 2020/8/28 9:49
@description
 */
class HomePagerAdapter(private var fm: FragmentManager?, private var datas: Array<CHANNEL>?) :
    FragmentPagerAdapter(fm!!) {

    //这种方式，避免一次性创建所有的framgent
    override fun getItem(position: Int): Fragment {
//        return when (datas!![position].getValue()) {
//            CHANNEL.MINE_ID -> MineFragment.newInstance()
//            CHANNEL.DISCORY_ID -> DiscoryFragment.newInstance()
//            CHANNEL.FRIEND_ID -> newInstance()
//            CHANNEL.VIDEO_ID -> VideoFragment.newInstance()
//            else -> MineFragment.newInstance()
//        }
        return  MineFragment.newInstance()
    }

    override fun getCount(): Int {
        return datas?.size ?: 0
    }

}
