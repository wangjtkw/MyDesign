package com.example.mydesign.selfcognition

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.mydesign.R
import com.example.mydesign.curriculumvitae.MineInfoCurriculumVitaeActivity
import com.example.mydesign.utils.StatusBarUtils

/**
 * 自我简介
 */
class MineInfoSelfCognitionActivity : AppCompatActivity() {
    private lateinit var backButton: FrameLayout
    private lateinit var descriptionLayout: LinearLayout
    private lateinit var descriptionTextView: TextView
    private lateinit var selfTagLayout: LinearLayout
    private lateinit var nextStepButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        StatusBarUtils.setLightBar(this, Color.TRANSPARENT)
        setContentView(R.layout.activity_mine_info_self_cognition)
        init()
        initListener()
    }

    private fun initListener() {
        backButton.setOnClickListener {
            finish()
        }
        descriptionLayout.setOnClickListener {
            val intent = Intent(this, SelfDescriptionActivity::class.java)
            startActivityForResult(intent, SELF_DESCRIPTION_REQUEST_CODE)
        }
        nextStepButton.setOnClickListener {
            val intent = Intent(this, MineInfoCurriculumVitaeActivity::class.java)
            startActivity(intent)
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
    }
}