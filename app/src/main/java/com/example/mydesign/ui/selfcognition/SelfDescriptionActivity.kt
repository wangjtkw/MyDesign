package com.example.mydesign.ui.selfcognition

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.mydesign.R
import com.example.mydesign.data.bean.PersonInfo1Bean
import com.example.mydesign.databinding.ActivityMineInfoBinding
import com.example.mydesign.databinding.ActivitySelfDescriptionBinding
import com.example.mydesign.utils.StatusBarUtils
import com.example.mydesign.utils.ToastUtil

/**
 * 自我描述
 */
class SelfDescriptionActivity : AppCompatActivity() {
    private var mBinding: ActivitySelfDescriptionBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        StatusBarUtils.setLightBar(this, Color.TRANSPARENT)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_self_description)
        observeData()
        initListener()
    }

    private fun observeData() {
        PersonInfo1Bean.description.observe(this) {
            mBinding!!.activitySelfDescriptionEditText.setText(it)
        }
    }

    private fun initListener() {
        mBinding!!.activitySelfDescriptionCancelLayout.setOnClickListener {
            finish()
        }
        mBinding!!.activitySelfDescriptionSaveButton.setOnClickListener {
            val description = mBinding!!.activitySelfDescriptionEditText.text.toString()
            if (description.isEmpty()) {
                ToastUtil.makeToast("请输入自我描述！")
            } else {
                PersonInfo1Bean.description.value = description
                finish()
            }
        }
    }

}