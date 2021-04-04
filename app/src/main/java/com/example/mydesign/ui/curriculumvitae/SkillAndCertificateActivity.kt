package com.example.mydesign.ui.curriculumvitae

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.mydesign.R
import com.example.mydesign.utils.StatusBarUtils

/**
 * 技能和证书
 */
class SkillAndCertificateActivity : AppCompatActivity() {
    private lateinit var backLayout: FrameLayout
    private lateinit var addTargetLayout: LinearLayout
    private lateinit var addItemLayout: LinearLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        StatusBarUtils.setLightBar(this, Color.TRANSPARENT)
        setContentView(R.layout.activity_skill_and_certificate)
        init()
        initListener()
    }

    private fun init() {
        backLayout = f(R.id.activity_curriculum_vitae_skill_and_certificate_back_layout)
        addTargetLayout = f(R.id.activity_curriculum_vitae_skill_and_certificate_add_target_layout)
        addItemLayout = f(R.id.activity_curriculum_vitae_skill_and_certificate_add_item_layout)
    }

    private fun initListener() {
        backLayout.setOnClickListener {
            finish()
        }
    }

    private fun <T : View> f(id: Int): T {
        return findViewById(id)
    }
}