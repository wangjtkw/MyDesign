package com.example.mydesign.ui.mine

import android.content.Intent
import android.widget.FrameLayout
import android.widget.LinearLayout
import com.example.mydesign.ui.mineinfo.MineInfoActivity
import com.example.mydesign.R
import com.example.mydesign.base.BaseFragment

class MineFragment : BaseFragment() {
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

    override fun init() {
        headLayout = f(R.id.fragment_mine_head_name_view)
        allRecordLayout = f(R.id.fragment_mine_num_all_record_layout)
        signUpLayout = f(R.id.fragment_mine_num_sign_up_layout)
        enterLayout = f(R.id.fragment_mine_num_enter_layout)
        finishLayout = f(R.id.fragment_mine_num_finish_layout)
        myResumeLayout = f(R.id.fragment_mine_my_resume_layout)
        myCollection = f(R.id.fragment_mine_my_collection_layout)
        changeToEmployerLayout = f(R.id.fragment_mine_change_to_employer_layout)
        settingLayout = f(R.id.fragment_mine_setting_layout)
        setListener()
    }

    private fun setListener() {
        headLayout.setOnClickListener {
            requireContext().startActivity(Intent(requireContext(), MineInfoActivity::class.java))
        }
    }

    override fun getLayoutID() = R.layout.fragment_mine

}