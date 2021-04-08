package com.example.mydesign.ui.positiondetail

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.mydesign.R
import com.example.mydesign.databinding.ActivityLoginBinding
import com.example.mydesign.databinding.ActivityPositionDetailBinding
import com.example.mydesign.ui.login.LoginViewModel
import com.example.mydesign.utils.ToastUtil
import com.example.mydesignapplication.data.bean.Status
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class PositionDetailActivity : AppCompatActivity(), HasAndroidInjector {
    private var paramPositionId: Int? = null

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>
    override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: PositionDetailViewModel by viewModels { viewModelFactory }

    private var mBinding: ActivityPositionDetailBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_position_detail)
        init()
    }

    private fun init() {
        initParam()
        getData()
    }

    private fun initParam() {
        paramPositionId = intent.getIntExtra(PARAM_POSITION_ID, 1)
        if (paramPositionId == null || paramPositionId == -1) {
            ToastUtil.makeToast("参数错误！")
            finish()
        }
    }

    private fun getData() {
        viewModel.getJobDetail(paramPositionId!!).observe(this) {
            when (it.status) {
                Status.SUCCESS -> {
                    mBinding!!.jobPositionEntity = it.data
                }
            }
        }
    }

    companion object {

        const val PARAM_POSITION_ID = "param_position_id"

        fun actionStart(context: Context, positionId: Int) {
            val intent = Intent(context, PositionDetailActivity::class.java)
            intent.putExtra(PARAM_POSITION_ID, positionId)
            context.startActivity(intent)
        }
    }

}