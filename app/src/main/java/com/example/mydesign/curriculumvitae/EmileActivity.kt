package com.example.mydesign.curriculumvitae

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.mydesign.R
import com.example.mydesign.utils.StatusBarUtils

/**
 * 邮箱
 */
class EmileActivity : AppCompatActivity() {
    private lateinit var backLayout: FrameLayout
    private lateinit var saveLayout: FrameLayout
    private lateinit var emileEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        StatusBarUtils.setLightBar(this, Color.TRANSPARENT)
        setContentView(R.layout.activity_emile)
        init()
        initListener()
    }

    private fun init() {
        backLayout = f(R.id.activity_curriculum_vitae_emile_back_layout)
        saveLayout = f(R.id.activity_curriculum_vitae_emile_save_layout)
        emileEditText = f(R.id.activity_curriculum_vitae_emile_edit_text)
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