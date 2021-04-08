package com.example.mydesign.ui.selfcognition

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.mydesign.R
import com.example.mydesign.data.bean.PersonInfo1Bean
import com.example.mydesign.databinding.ActivityMineInfoBinding
import com.example.mydesign.databinding.ActivityMineInfoSelfCognitionBinding
import com.example.mydesign.ui.curriculumvitae.MineInfoCurriculumVitaeActivity
import com.example.mydesign.ui.main.MainActivity
import com.example.mydesign.ui.mineinfo.MineInfoViewModel
import com.example.mydesign.utils.StatusBarUtils
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

/**
 * 自我简介
 */
class MineInfoSelfCognitionActivity : AppCompatActivity(), HasAndroidInjector {
    private lateinit var backButton: FrameLayout
    private lateinit var descriptionLayout: LinearLayout
    private lateinit var descriptionTextView: TextView
    private lateinit var selfTagLayout: LinearLayout
    private lateinit var nextStepButton: Button

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>
    override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector

    private var mBinding: ActivityMineInfoSelfCognitionBinding? = null

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: MineInfoSelfCognitionViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        StatusBarUtils.setLightBar(this, Color.TRANSPARENT)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_mine_info_self_cognition)
        init()
        observeData()
        initListener()
    }

    private fun observeData() {
        PersonInfo1Bean.description.observe(this) {
            mBinding!!.mineInfoSelfCognitionSelfDescriptionTextView.text = it
        }
        viewModel.updateDescriptionResult.observe(this) {

        }
    }

    private fun initListener() {
        mBinding!!.mineInfoSelfCognitionBackLayout.setOnClickListener {
            finish()
        }
        mBinding!!.mineInfoSelfCognitionSelfDescriptionLayout.setOnClickListener {
            val intent = Intent(this, SelfDescriptionActivity::class.java)
            startActivity(intent)
        }
        mBinding!!.mineInfoSelfCognitionSelfNextStepButton.setOnClickListener {
//            val intent = Intent(this, MineInfoCurriculumVitaeActivity::class.java)
//            startActivity(intent)
            viewModel.updateDescription(MainActivity.USER_ACCOUNT_BEAN!!.usersId!!) {
                MainActivity.actionStart(this, MainActivity.USER_ACCOUNT_BEAN!!)
            }
        }
    }

    private fun init() {
        backButton = f(R.id.mine_info_self_cognition_back_layout)
        descriptionLayout = f(R.id.mine_info_self_cognition_self_description_layout)
        descriptionTextView = f(R.id.mine_info_self_cognition_self_description_text_view)
        selfTagLayout = f(R.id.mine_info_self_cognition_self_tag_layout)
        nextStepButton = f(R.id.mine_info_self_cognition_self_next_step_button)
    }

    private fun <T : View> f(id: Int): T {
        return findViewById(id)
    }

    companion object {
        const val SELF_DESCRIPTION_REQUEST_CODE = 0x0000001
        const val SELF_DESCRIPTION = "self_description"

        fun actionStart(context: Context) {
            val intent = Intent(context, MineInfoSelfCognitionActivity::class.java)
            context.startActivity(intent)
        }

    }
}