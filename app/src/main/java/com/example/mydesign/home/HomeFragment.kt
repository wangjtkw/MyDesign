package com.example.mydesign.home

import androidx.fragment.app.Fragment
import android.widget.LinearLayout
import androidx.viewpager2.widget.ViewPager2
import com.example.mydesign.base.BaseFragment
import com.example.mydesign.R
import com.example.mydesign.publicclass.TabPagerBinding
import com.example.mydesign.publicclass.ViewPagerAdapter
import com.google.android.material.tabs.TabLayout

class HomeFragment : BaseFragment() {
    private lateinit var localLayout: LinearLayout
    private lateinit var searchLayout: LinearLayout
    private lateinit var tabLayout: TabLayout
    private val fragmentList = ArrayList<Fragment>()
    private lateinit var viewPager: ViewPager2
    private lateinit var viewPagerAdapter: ViewPagerAdapter


    override fun init() {
        localLayout = f(R.id.fragment_home_local_layout)
        searchLayout = f(R.id.fragment_home_search_layout)
        tabLayout = f(R.id.fragment_home_tab_layout)
        viewPager = f(R.id.fragment_home_view_pager)
        initLocalLayout()
        initSearchLayout()
        initViewPager()
        initTabLayout()
        TabPagerBinding.bindTabPager(tabLayout, viewPager)
    }


    private fun initLocalLayout() {

    }

    private fun initViewPager() {
        fragmentList.apply {
            add(HomePageFragment.newInstance("0", true, true, true, true))
            add(HomePageFragment.newInstance("1", true, true, true))
            add(HomePageFragment.newInstance("2", true, true))
            add(HomePageFragment.newInstance("3", true))
            add(HomePageFragment.newInstance("4"))
        }
        viewPagerAdapter = ViewPagerAdapter(fragmentList, requireActivity())
        viewPager.adapter = viewPagerAdapter
        viewPager.currentItem = 0
    }

    private fun initSearchLayout() {

    }


    private fun initTabLayout() {
        tabLayout.apply {
            addTab(newTab().setText("推荐"))
            addTab(newTab().setText("在家做"))
            addTab(newTab().setText("在本地"))
            addTab(newTab().setText("主播"))
            addTab(newTab().setText("文案编辑"))
        }
    }

    override fun getLayoutID() = R.layout.fragment_home

}