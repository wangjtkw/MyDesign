package com.example.mydesign.curriculumvitae

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.mydesign.R
import com.example.mydesign.utils.StatusBarUtils

class InternshipExperienceFillActivity : AppCompatActivity() {
    private lateinit var backLayout: FrameLayout
    private lateinit var saveLayout: FrameLayout
    private lateinit var corporateNameEditText: EditText
    private lateinit var positionNameEditText: EditText
    private lateinit var startTimeTextView: TextView
    private lateinit var endTimeTextView: TextView
    private lateinit var describeEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        StatusBarUtils.setLightBar(this, Color.TRANSPARENT)
        setContentView(R.layout.activity_internship_experience_fill)
        init()
        initListener()
    }

    private fun init() {
        backLayout = f(R.id.activity_curriculum_vitae_internship_experience_fill_back_layout)
        saveLayout = f(R.id.activity_curriculum_vitae_internship_experience_fill_save_layout)
        corporateNameEditText =
            f(R.id.activity_curriculum_vitae_internship_experience_fill_corporate_name_edit_text)
        positionNameEditText =
            f(R.id.activity_curriculum_vitae_internship_experience_fill_position_name_edit_text)
        startTimeTextView =
            f(R.id.activity_curriculum_vitae_internship_experience_fill_start_time_text_view)
        endTimeTextView =
            f(R.id.activity_curriculum_vitae_internship_experience_fill_end_time_text_view)
        describeEditText =
            f(R.id.activity_curriculum_vitae_internship_experience_fill_describe_edit_text)
    }

    private fun initListener() {
        backLayout.setOnClickListener {
            finish()
        }
        saveLayout.setOnClickListener {
            val intent = Intent()
            setResult(RESULT_OK, intent)
            finish()
        }
    }


    private fun <T : View> f(id: Int): T {
        return findViewById(id)
    }
}