package com.example.mydesign.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mydesign.base.BaseFragment
import com.example.mydesign.R
import com.example.mydesign.databinding.FragmentHomePageBinding
import com.example.mydesign.databinding.FragmentMineBinding
import com.example.mydesign.ui.mine.MineViewModel
import com.example.mydesignapplication.data.bean.Status
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject


private const val ARG_PARAM_CONSTRAIN_SHOW = "constrain_show"
private const val ARG_PARAM_JOB_TYPE_SHOW = "job_type_show"
private const val ARG_PARAM_SORT_SHOW = "sort_show"
private const val ARG_PARAM_SCREEN_SHOW = "screen_show"
private const val ARG_PARAM_TYPE = "type"

class HomePageFragment : Fragment() {
    private var paramType: String? = null
    private var paramConstrainShow: Boolean = false
    private var paramJobTypeShow: Boolean = false
    private var paramSortShow: Boolean = false
    private var paramScreenShow: Boolean = false

    private lateinit var recyclerAdapter: RecyclerAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private var mBinding: FragmentHomePageBinding? = null

    private val viewModel: HomePageViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
        initParameters()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.fragment_home_page,
            container,
            false,
//            dataBindingComponent
        )
        init()
        return mBinding!!.root
    }


    private fun initParameters() {
        arguments?.apply {
            paramType = getString(ARG_PARAM_TYPE, null)
            paramConstrainShow = getBoolean(ARG_PARAM_CONSTRAIN_SHOW)
            paramJobTypeShow = getBoolean(ARG_PARAM_JOB_TYPE_SHOW)
            paramScreenShow = getBoolean(ARG_PARAM_SCREEN_SHOW)
            paramSortShow = getBoolean(ARG_PARAM_SORT_SHOW)
        }
    }

    fun init() {
//        homePageConstraintLayout = f(R.id.fragment_home_page_constraintLayout)
//        jobTypeLayout = f(R.id.fragment_home_page_job_type_layout)
//        sortLayout = f(R.id.fragment_home_page_sort_layout)
//        screenLayout = f(R.id.fragment_home_page_screen_layout)
//        initViewShow()
        initJobLayout()
        initSortLayout()
        initScreenLayout()
        initRecyclerView()
    }

//    private fun initViewShow() {
//        homePageConstraintLayout.visibility = if (paramConstrainShow) {
//            View.VISIBLE
//        } else {
//            View.GONE
//        }
//        jobTypeLayout.visibility = if (paramJobTypeShow) {
//            View.VISIBLE
//        } else {
//            View.GONE
//        }
//        screenLayout.visibility = if (paramScreenShow) {
//            View.VISIBLE
//        } else {
//            View.GONE
//        }
//        sortLayout.visibility = if (paramSortShow) {
//            View.VISIBLE
//        } else {
//            View.GONE
//        }
//    }

    private fun initJobLayout() {

    }

    private fun initSortLayout() {

    }

    private fun initScreenLayout() {

    }

    private fun initRecyclerView() {
        recyclerAdapter = RecyclerAdapter()
        mBinding!!.fragmentHomePageRecyclerview.adapter = recyclerAdapter
        mBinding!!.fragmentHomePageRecyclerview.layoutManager =
            LinearLayoutManager(requireContext())
        viewModel.getJobList(paramType).observe(requireActivity()) {
            when (it.status) {
                Status.SUCCESS -> {
                    if (!it.data.isNullOrEmpty()) {
                        recyclerAdapter.addData(it.data)
                    }
                }
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(
            typeTag: String?,
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