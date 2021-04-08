package com.example.mydesign.ui.mine

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.mydesign.ui.mineinfo.MineInfoActivity
import com.example.mydesign.R
import com.example.mydesign.base.BaseFragment
import com.example.mydesign.data.bean.PersonInfo1Bean
import com.example.mydesign.databinding.FragmentMineBinding
import com.example.mydesign.ext.isConnectedNetwork
import com.example.mydesign.utils.ToastUtil
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class MineFragment : Fragment() {
    private lateinit var headLayout: LinearLayout

    //全部记录
    private lateinit var allRecordLayout: LinearLayout

    //已报名
    private lateinit var signUpLayout: LinearLayout

    //已录取
    private lateinit var enterLayout: LinearLayout

    //已结束
    private lateinit var finishLayout: LinearLayout
    private lateinit var myResumeLayout: FrameLayout
    private lateinit var myCollection: FrameLayout
    private lateinit var changeToEmployerLayout: FrameLayout
    private lateinit var settingLayout: FrameLayout

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private var mBinding: FragmentMineBinding? = null

    private val viewModel: MineViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.fragment_mine,
            container,
            false,
//            dataBindingComponent
        )
        init()
        return mBinding!!.root
    }


    fun init() {
        observeData()
        getData()
        setListener()
    }

    private fun getData() {
        viewModel.getInfo()
        if (!requireContext().isConnectedNetwork()) {
            ToastUtil.makeToast("当前网络未连接！")
        }
    }

    private fun observeData() {
        viewModel.getUserInfoResult.observe(requireActivity()) {
            mBinding!!.imgSrc = it.usersImg
        }
        PersonInfo1Bean.usersName.observe(this) {
            mBinding!!.name = it
        }
    }

    private fun setListener() {
        mBinding!!.fragmentMineHeadNameView.setOnClickListener {
            requireContext().startActivity(Intent(requireContext(), MineInfoActivity::class.java))
        }
    }

}