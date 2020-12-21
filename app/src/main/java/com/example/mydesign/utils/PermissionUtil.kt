package com.example.mydesign.utils

/*
class PermissionUtil {
    private var mActivity: Activity? = null
    private var requestPermission: String = ""
    private var requestPermissions: Array<String> = arrayOf("")
    private var requestPermissionMap: HashMap<String, Int> =
        mapOf<String, Int>() as HashMap<String, Int>
    private var mRequestCode = 0
    private var mSuccessCallback: () -> Unit = {}
    private var mPermissionDialogUtil: PermissionDialogUtil = AccessPermissionDialogUtil()
    private var mJumpCallback: () -> Unit = {}


    fun registerActivity(activity: Activity, permission: String, successCallback: () -> Unit) {
        mActivity = activity
        requestPermission = permission
        mSuccessCallback = successCallback
        checkPermission(requestPermission, true)
    }

    fun registerActivity(
        activity: Activity,
        permissions: Array<String>,
        successCallback: () -> Unit
    ) {
        mActivity = activity
        requestPermissions = permissions
        mSuccessCallback = successCallback
        for (s in requestPermissions) {
            requestPermissionMap[s] = mRequestCode++
        }

        for ((s, i) in requestPermissionMap) {
            checkPermission(s, false)
        }
    }

    fun setPermissionDialogUtil(permissionDialogUtil: PermissionDialogUtil) {
        mPermissionDialogUtil = permissionDialogUtil
    }

    fun setJumpCallback(jumpCallback: () -> Unit) {
        mJumpCallback = jumpCallback
    }

    fun unRegister() {
        mActivity = null
        requestPermission = ""
        requestPermissions = arrayOf("")
        requestPermissionMap.clear()
        mSuccessCallback = {}
    }

    private fun checkPermission(permission: String, isSingle: Boolean) {
        if (mActivity == null) {
            return
        }
        val request = ContextCompat.checkSelfPermission(
            mActivity!!.applicationContext,
            permission
        )
        if (request == PackageManager.PERMISSION_GRANTED) {
            onPermissionGranted(permission)
        } else {
            ActivityCompat.requestPermissions(
                mActivity!!,
                arrayOf(permission),
                requestPermissionMap[permission]!!
            );
        }
    }

    private fun onPermissionGranted(request: String) {
        if (requestPermissionMap.contains(request)) {
            requestPermissionMap.remove(request)
        }
        if (requestPermission.isNotEmpty() || requestPermissionMap.isEmpty()) {
            mSuccessCallback()
        }
    }

    fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == mRequestCode) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                mSuccessCallback()
            } else {
                //用户不同意，向用户展示该权限作用
                if (ActivityCompat.shouldShowRequestPermissionRationale(
                        mActivity!!,
                        permissions[0]
                    )
                ) {
                    mPermissionDialogUtil.initAccessPermissionDialog(mActivity)
                }
            }
        }
    }
}
*/