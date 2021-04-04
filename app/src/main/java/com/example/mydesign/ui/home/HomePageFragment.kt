package com.example.mydesign.ui.home

import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mydesign.base.BaseFragment
import com.example.mydesign.R


private const val ARG_PARAM_CONSTRAIN_SHOW = "constrain_show"
private const val ARG_PARAM_JOB_TYPE_SHOW = "job_type_show"
private const val ARG_PARAM_SORT_SHOW = "sort_show"
private const val ARG_PARAM_SCREEN_SHOW = "screen_show"
private const val ARG_PARAM_TYPE = "type"

class HomePageFragment : BaseFragment() {
    private var paramType: String = ""
    private var paramConstrainShow: Boolean = false
    private var paramJobTypeShow: Boolean = false
    private var paramSortShow: Boolean = false
    private var paramScreenShow: Boolean = false

    private lateinit var homePageConstraintLayout: ConstraintLayout
    private lateinit var jobTypeLayout: FrameLayout
    private lateinit var sortLayout: FrameLayout
    private lateinit var screenLayout: FrameLayout
    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerAdapter: RecyclerAdapter


    override fun initParameters() {
        arguments?.apply {
            paramType = getString(ARG_PARAM_TYPE, "")
            paramConstrainShow = getBoolean(ARG_PARAM_CONSTRAIN_SHOW)
            paramJobTypeShow = getBoolean(ARG_PARAM_JOB_TYPE_SHOW)
            paramScreenShow = getBoolean(ARG_PARAM_SCREEN_SHOW)
            paramSortShow = getBoolean(ARG_PARAM_SORT_SHOW)
        }
    }

    override fun init() {
//        homePageConstraintLayout = f(R.id.fragment_home_page_constraintLayout)
//        jobTypeLayout = f(R.id.fragment_home_page_job_type_layout)
//        sortLayout = f(R.id.fragment_home_page_sort_layout)
//        screenLayout = f(R.id.fragment_home_page_screen_layout)
        recyclerView = f(R.id.fragment_home_page_recyclerview)
//        initViewShow()
        initJobLayout()
        initSortLayout()
        initScreenLayout()
        initRecyclerView()
    }

    private fun initViewShow() {
        homePageConstraintLayout.visibility = if (paramConstrainShow) {
            View.VISIBLE
        } else {
            View.GONE
        }
        jobTypeLayout.visibility = if (paramJobTypeShow) {
            View.VISIBLE
        } else {
            View.GONE
        }
        screenLayout.visibility = if (paramScreenShow) {
            View.VISIBLE
        } else {
            View.GONE
        }
        sortLayout.visibility = if (paramSortShow) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

    private fun initJobLayout() {

    }

    private fun initSortLayout() {

    }

    private fun initScreenLayout() {

    }

    private fun initRecyclerView() {
        recyclerAdapter = RecyclerAdapter()
//        DataProvide.provideHomePageItem {
//            recyclerAdapter.addData(it)
//        }

        recyclerView.adapter = recyclerAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }


    override fun getLayoutID() = R.layout.fragment_home_page

    companion object {
        @JvmStatic
        fun newInstance(
            typeTag: String,
            showConstraintLayout: Boolean = false,
            showJobType: Boolean = false,
            showScreen: Boolean = false,
            showSort: Boolean = false
        ) =
            HomePageFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM_TYPE, typeTag)
                    putBoolean(ARG_PARAM_CONSTRAIN_SHOW, showConstraintLayout)
                    putBoolean(ARG_PARAM_JOB_TYPE_SHOW, showJobType)
                    putBoolean(ARG_PARAM_SCREEN_SHOW, showScreen)
                    putBoolean(ARG_PARAM_SORT_SHOW, showSort)
                }
            }
    }

}