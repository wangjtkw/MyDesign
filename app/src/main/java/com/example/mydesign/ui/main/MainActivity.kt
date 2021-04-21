package com.example.mydesign.ui.main

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.mydesign.MessageFragment
import com.example.mydesign.R
import com.example.mydesign.data.bean.entity.UserAccountEntity
import com.example.mydesign.netty.NettyService
import com.example.mydesign.ui.home.HomeFragment
import com.example.mydesign.ui.mine.MineFragment
import com.example.mydesign.publicclass.TabPagerBinding
import com.example.mydesign.publicclass.ViewPagerAdapter
import com.example.mydesign.utils.StatusBarUtils
import com.google.android.material.tabs.TabLayout
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject


class MainActivity : AppCompatActivity(), HasAndroidInjector {
    private lateinit var mainTabLayout: TabLayout
    private lateinit var mainViewPager: ViewPager2
    private lateinit var viewPagerAdapter: ViewPagerAdapter
    private val fragmentList = ArrayList<Fragment>()

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>
    override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        StatusBarUtils.setLightBar(this, Color.TRANSPARENT)
        setContentView(R.layout.activity_main)
        init()
        startService()
    }

    private fun startService(){
        val intent = Intent(this,NettyService::class.java)
        startService(intent)
    }

    private fun init() {
        mainTabLayout = f(R.id.main_tab_layout)
        mainViewPager = f(R.id.main_view_pager)
        initTabLayout()
        initViewPager()
        TabPagerBinding.bindTabPager(mainTabLayout, mainViewPager)
    }

    private fun initViewPager() {
        fragmentList.apply {
            add(HomeFragment())
            add(MessageFragment())
            add(MineFragment())
        }
        viewPagerAdapter = ViewPagerAdapter(fragmentList, this)
        mainViewPager.adapter = viewPagerAdapter
        mainViewPager.currentItem = 0
    }

    private fun initTabLayout() {
        mainTabLayout.apply {
            addTab(newTab().setText("首页"))
            addTab(newTab().setText("消息"))
            addTab(newTab().setText("我的"))
        }
    }

    private fun <T : View> f(id: Int): T {
        return findViewById(id)
    }

    companion object {
        var USER_ACCOUNT_BEAN: UserAccountEntity? = null

        fun actionStart(context: Context, userAccountBean: UserAccountEntity) {
            val intent = Intent(context, MainActivity::class.java)
            USER_ACCOUNT_BEAN = userAccountBean
            context.startActivity(intent)
        }
    }
}