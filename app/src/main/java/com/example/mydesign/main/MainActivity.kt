package com.example.mydesign.main

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.mydesign.*
import com.example.mydesign.home.HomeFragment
import com.example.mydesign.mine.MineFragment
import com.example.mydesign.publicclass.TabPagerBinding
import com.example.mydesign.publicclass.ViewPagerAdapter
import com.google.android.material.tabs.TabLayout


class MainActivity : AppCompatActivity() {
    private lateinit var mainTabLayout: TabLayout
    private lateinit var mainViewPager: ViewPager2
    private lateinit var viewPagerAdapter: ViewPagerAdapter
    private val fragmentList = ArrayList<Fragment>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        StatusBarUtils.setLightBar(this, Color.TRANSPARENT)
        setContentView(R.layout.activity_main)
        init()
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
}