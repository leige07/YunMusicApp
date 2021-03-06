package com.test.yunmusicapp

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.drawerlayout.widget.DrawerLayout
import androidx.viewpager.widget.ViewPager
import com.test.yunmusicapp.model.CHANNEL
import com.test.yunmusicapp.view.adapter.HomePagerAdapter
import com.ygsoft.lib_common_ui.base.BaseActivity
import com.ygsoft.lib_common_ui.pager_indictor.ScaleTransitionPagerTitleView
import com.ygsoft.lib_network.CommonOkHttpClient
import com.ygsoft.lib_network.TestData
import com.ygsoft.lib_network.YgHttpClient
import com.ygsoft.lib_network.eception.OkHttpException
import com.ygsoft.lib_network.listener.DisposeDataHandle
import com.ygsoft.lib_network.listener.DisposeDataListener
import com.ygsoft.lib_network.request.CommonRequest
import com.ygsoft.lib_network.response.CommonJsonCallback
import net.lucode.hackware.magicindicator.MagicIndicator
import net.lucode.hackware.magicindicator.ViewPagerHelper
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView


class MainActivity : BaseActivity(), View.OnClickListener {

    private val CHANNELS = arrayOf(CHANNEL.MY, CHANNEL.DISCORY, CHANNEL.FRIEND)

    private var mDrawerLayout: DrawerLayout? = null
    private var mToggleView: View? = null
    private var mSearchView: View? = null
    private var mViewPager: ViewPager? = null
    private var mAdapter: HomePagerAdapter? = null
    private var mDrawerQrcodeView: View? = null
    private var mDrawerShareView: View? = null
    private var unLogginLayout: View? = null
    private var mPhotoView: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        registerBroadcastReceiver()
//        EventBus.getDefault().register(this)
        setContentView(R.layout.activity_main)
        initView()
        initData()
    }

    private fun registerBroadcastReceiver() {

    }

    private fun initData() {
//        CommonOkHttpClient.getRequest(CommonRequest.createGetRequest("http://api.androidhive.info/volley/person_object.json"),
//           CommonJsonCallback(TestData::class.java, object : DisposeDataListener<TestData>{
//               override fun onSuccess(responseObj: TestData) {
//                   Log.e("TAG", responseObj.toString())
//               }
//
//               override fun onFailure(exception: OkHttpException) {
//                   Log.e("TAG", "${exception.getCode()}  ${exception.getMsg()}")
//               }
//           })
//        )

        YgHttpClient.getInstance().getRequest(CommonRequest.createGetRequest("http://api.androidhive.info/volley/person_object.json"),
            DisposeDataHandle(object : DisposeDataListener<TestData>{
                override fun onSuccess(response: TestData) {
                    Log.e("TAG", response.toString())
                }

                override fun onFailure(exception: OkHttpException) {
                    Log.e("TAG", "${exception.getCode()}  ${exception.getMsg()}")
                }
            }, TestData::class.java)
        )
    }

    private fun initView() {
        mDrawerLayout = findViewById(R.id.drawer_layout)
        mToggleView = findViewById(R.id.toggle_view)
        mToggleView?.setOnClickListener(this)
        mSearchView = findViewById(R.id.search_view)
        mSearchView?.setOnClickListener(this)
        //初始化adpater
        mAdapter = HomePagerAdapter(supportFragmentManager, CHANNELS)
        mViewPager = findViewById(R.id.view_pager)
        mViewPager?.adapter = mAdapter
        initMagicIndicator()

        mDrawerQrcodeView = findViewById(R.id.home_qrcode)
        mDrawerQrcodeView?.setOnClickListener(this)
        mDrawerShareView = findViewById(R.id.home_music)
        mDrawerShareView?.setOnClickListener(this)
        findViewById<View>(R.id.online_music_view).setOnClickListener(this)
        findViewById<View>(R.id.check_update_view).setOnClickListener(this)

        unLogginLayout = findViewById(R.id.unloggin_layout)
        unLogginLayout?.setOnClickListener(this)
        mPhotoView = findViewById(R.id.avatr_view)
        findViewById<View>(R.id.exit_layout).setOnClickListener(this)
    }

    private fun initMagicIndicator() {
        val magicIndicator: MagicIndicator = findViewById(R.id.magic_indicator)
        magicIndicator.setBackgroundColor(Color.WHITE)
        val commonNavigator = CommonNavigator(this)
        commonNavigator.isAdjustMode = true
        commonNavigator.adapter = object : CommonNavigatorAdapter() {
            override fun getCount(): Int {
                return CHANNELS?.size ?: 0
            }

            override fun getTitleView(context: Context?, index: Int): IPagerTitleView? {
                val simplePagerTitleView: SimplePagerTitleView = ScaleTransitionPagerTitleView(
                    context
                )
                simplePagerTitleView.text = CHANNELS[index].getKey()
                simplePagerTitleView.textSize = 19f
                simplePagerTitleView.typeface = Typeface.defaultFromStyle(Typeface.BOLD)
                simplePagerTitleView.normalColor = Color.parseColor("#999999")
                simplePagerTitleView.selectedColor = Color.parseColor("#333333")
                simplePagerTitleView.setOnClickListener { mViewPager!!.currentItem = index }
                return simplePagerTitleView
            }

            override fun getIndicator(context: Context?): IPagerIndicator? {
                return null
            }

            override fun getTitleWeight(context: Context?, index: Int): Float {
                return 1.0f
            }
        }
        magicIndicator.navigator = commonNavigator
        ViewPagerHelper.bind(magicIndicator, mViewPager)
    }

    override fun onClick(v: View) {

    }

}