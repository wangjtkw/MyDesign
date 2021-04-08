package com.example.mydesign.publicclass

import android.util.Log
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout

object TabPagerBinding {
    private val TAG = "TabPagerBinding"

    fun bindTabPager(
        tabLayout: TabLayout,
        viewPager2: ViewPager2,
        tabCallBack: ((tab: TabLayout.Tab?) -> Unit)? = null,
        pagerCallBack: ((position: Int) -> Unit)? = null
    ) {
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                Log.d(TAG, "tabIndex:${tab?.position}")
                if (tabCallBack == null) {
                    viewPager2.currentItem = tab?.position ?: 0
                } else {
                    tabCallBack(tab)
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                Log.d(TAG,"viewpagerIndex:$position")
                if (pagerCallBack == null) {
                    tabLayout.getTabAt(position)?.select()
                } else {
                    pagerCallBack(position)
                }

            }
        })
    }
}