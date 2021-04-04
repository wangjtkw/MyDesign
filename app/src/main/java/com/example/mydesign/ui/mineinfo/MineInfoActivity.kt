package com.example.mydesign.ui.mineinfo

import android.Manifest
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.mydesign.R
import com.example.mydesign.ui.album.AlbumActivity
import com.example.mydesign.base.PermissionActivity
import com.example.mydesign.data.bean.AlbumBean
import com.example.mydesign.data.bean.PersonInfo1Bean
import com.example.mydesign.databinding.ActivityMineInfoBinding
import com.example.mydesign.dialog.ChooseDateDialogUtil
import com.example.mydesign.dialog.MineInfoSingleDataDialogUtil
import com.example.mydesign.ext.isConnectedNetwork
import com.example.mydesign.ui.main.MainActivity
import com.example.mydesign.ui.mineinfoeducation.MineInfoEducationActivity
import com.example.mydesign.ui.selfcognition.MineInfoSelfCognitionActivity
import com.example.mydesign.utils.StatusBarUtils
import com.example.mydesign.utils.ToastUtil
import com.example.mydesignapplication.data.bean.Status
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

/**
 * 我的信息
 */
class MineInfoActivity : PermissionActivity(), HasAndroidInjector {
    private val TAG = "MineInfoActivity"

    private lateinit var sexDataList: List<String>
    private lateinit var roleDataList: List<String>

    private lateinit var sexSelectCallback: (Int) -> Unit
    private lateinit var birthdaySelectCallBack: (String) -> Unit
    private lateinit var roleSelectCallback: (Int) -> Unit

    private var mBinding: ActivityMineInfoBinding? = null

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>
    override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: MineInfoViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        StatusBarUtils.setLightBar(this, Color.TRANSPARENT)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_mine_info)
        init()
    }

    private fun init() {
        initData()
        observeData()
        getData()
        initListener()
        setListener()
    }

    private fun initData() {
        sexDataList = listOf("男", "女")
        roleDataList = listOf("学生党", "上班族", "自由职业")

    }

    private fun getData() {
        Log.d(TAG, "usersId:${MainActivity.USER_ACCOUNT_BEAN?.usersId}")
        if (MainActivity.USER_ACCOUNT_BEAN?.usersId != null) {
            viewModel.getInfo(MainActivity.USER_ACCOUNT_BEAN?.usersId!!)
        }
    }

    private fun observeData() {
        PersonInfo1Bean.apply {
            usersImg.observe(this@MineInfoActivity) {
                val uri = Uri.parse(it.DATA)
                mBinding!!.activityMineHeadSelectImageView.setImageURI(uri)
            }
            usersName.observe(this@MineInfoActivity) {
                mBinding!!.activityMineNameEditText.setText(it)
            }
            usersSex.observe(this@MineInfoActivity) {
                mBinding!!.activityMineSexSelectTextView.text = it
            }
            usersBirthday.observe(this@MineInfoActivity) {
                mBinding!!.activityMineBirthdaySelectTextView.text = it
            }
            usersRole.observe(this@MineInfoActivity) {
                mBinding!!.activityMineRoleSelectTextView.text = it
            }
            usersPhoneNum.observe(this@MineInfoActivity) {
                mBinding!!.activityMinePhoneNumTextView.text = it
            }
            usersWechat.observe(this@MineInfoActivity) {
                mBinding!!.activityMineWechatEditText.setText(it)
            }
            usersQq.observe(this@MineInfoActivity) {
                mBinding!!.activityMineQqEditText.setText(it)
            }
        }
        viewModel.insertPersonInfoResult.observe(this) {

        }
        viewModel.getUserInfoResult.observe(this) {
            Log.d(TAG, it.toString())
            mBinding!!.imgSrc = it.usersImg
        }
        viewModel.updateHeadImgResult.observe(this){

        }
    }

    private fun initListener() {
        sexSelectCallback = { i ->
            mBinding!!.activityMineSexSelectTextView.text = sexDataList[i]
            mBinding!!.activityMineSexSelectTextView.setTextColor(Color.parseColor("#8A000000"))
        }
        birthdaySelectCallBack = { s ->
            mBinding!!.activityMineBirthdaySelectTextView.text = s
            mBinding!!.activityMineBirthdaySelectTextView.setTextColor(Color.parseColor("#8A000000"))
        }
        roleSelectCallback = { i ->
            mBinding!!.activityMineRoleSelectTextView.text = roleDataList[i]
            mBinding!!.activityMineRoleSelectTextView.setTextColor(Color.parseColor("#8A000000"))
        }
        mBinding!!.activityMineEducationSelectLayout.setOnClickListener {
            val intent = Intent(this, MineInfoEducationActivity::class.java)

            startActivityForResult(intent, EDUCATION_EXPERIENCE_REQUEST_CODE)
        }
        mBinding!!.activityMineNextStepButton.setOnClickListener {
            val intent = Intent(this, MineInfoSelfCognitionActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setListener() {
        mBinding!!.activityMineBackLayout.setOnClickListener {
            this.finish()
        }
        mBinding!!.activityMineSaveLayout.setOnClickListener {
            if (isConnectedNetwork()) {
                if (MainActivity.USER_ACCOUNT_BEAN!!.usersId == null && checkInsert()) {
                    viewModel.insertUserInfo(MainActivity.USER_ACCOUNT_BEAN!!.usersAccountId) {
                        finish()
                    }
                }
            } else {
                ToastUtil.makeToast("当前网络未连接！")
            }
        }
        mBinding!!.activityMineNextStepButton.setOnClickListener {
            if (isConnectedNetwork()) {
                if (MainActivity.USER_ACCOUNT_BEAN!!.usersId == null && checkInsert()) {
                    viewModel.insertUserInfo(MainActivity.USER_ACCOUNT_BEAN!!.usersAccountId) {
                        finish()
                    }
                }
            } else {
                ToastUtil.makeToast("当前网络未连接！")
            }
        }
        mBinding!!.activityMineHeadSelectLayout.setOnClickListener {
            checkPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
        }
        mBinding!!.activityMineSexSelectLayout.setOnClickListener {
            MineInfoSingleDataDialogUtil().init(this, sexDataList, sexSelectCallback)
        }
        mBinding!!.activityMineBirthdaySelectLayout.setOnClickListener {
            ChooseDateDialogUtil().init(this, birthdaySelectCallBack)
        }
        mBinding!!.activityMineRoleSelectLayout.setOnClickListener {
            MineInfoSingleDataDialogUtil().init(this, roleDataList, roleSelectCallback)
        }
    }

    private fun checkInsert(): Boolean {
        val headImg = PersonInfo1Bean.usersImg.value
        val name = mBinding!!.activityMineNameEditText.text.toString()
        val sex = mBinding!!.activityMineSexSelectTextView.text.toString()
        val birthday = mBinding!!.activityMineBirthdaySelectTextView.text.toString()
        val role = mBinding!!.activityMineRoleSelectTextView.text.toString()
        val phoneNum = mBinding!!.activityMinePhoneNumTextView.text.toString()
        val weChat = mBinding!!.activityMineWechatEditText.text.toString()
        val qq = mBinding!!.activityMineQqEditText.text.toString()
        if (headImg == null) {
            ToastUtil.makeToast("请选择头像！")
            return false
        }
        if (name.isEmpty()) {
            ToastUtil.makeToast("请输入姓名！")
            return false
        }
        if (sex.isEmpty() || sex == "请选择") {
            ToastUtil.makeToast("请选择性别！")
            return false
        }
        if (birthday.isEmpty() || birthday == "请选择") {
            ToastUtil.makeToast("请选择生日！")
            return false
        }
        if (role.isEmpty() || role == "请选择") {
            ToastUtil.makeToast("请输入职业！")
            return false
        }
        if (phoneNum.isEmpty()) {
            ToastUtil.makeToast("请输入电话！")
            return false
        }
        if (!PersonInfo1Bean.isSelectEducation) {
            ToastUtil.makeToast("请输入教育经历！")
            return false
        }
        PersonInfo1Bean.apply {
            usersName.value = name
            usersSex.value = sex
            usersBirthday.value = birthday
            usersRole.value = role
            usersPhoneNum.value = phoneNum
            usersWechat.value = weChat
            usersQq.value = qq
        }
        return true

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ALBUM_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            val result = data.getParcelableExtra<AlbumBean>(ALBUM_BEAN)
            PersonInfo1Bean.usersImg.value = result
            if (MainActivity.USER_ACCOUNT_BEAN!!.usersId != null) {
                if (isConnectedNetwork()) {
                    viewModel.updateHeadImg(MainActivity.USER_ACCOUNT_BEAN!!.usersId!!)
                } else {
                    ToastUtil.makeToast("当前网络未连接！")
                }
            }
            val uri = Uri.parse(result!!.DATA)
            mBinding!!.activityMineHeadSelectImageView.setImageURI(uri)
            Log.d(TAG, result.toString())
        }
    }


    override fun doOnGetPermission(permission: String) {
        super.doOnGetPermission(permission)
        when (permission) {
            Manifest.permission.READ_EXTERNAL_STORAGE -> {
                val intent = Intent(this, AlbumActivity::class.java)
                startActivityForResult(intent, ALBUM_ACTIVITY_REQUEST_CODE)
            }
        }
    }

    private fun <T : View> f(id: Int): T {
        return findViewById(id)
    }


    companion object {
        // 教育经历界面
        const val EDUCATION_EXPERIENCE = "educationExperience"
        const val EDUCATION_EXPERIENCE_REQUEST_CODE = 0x000001

        //相册界面
        const val ALBUM_BEAN = "album_bean"
        const val ALBUM_ACTIVITY_REQUEST_CODE = 0x000002
    }
}