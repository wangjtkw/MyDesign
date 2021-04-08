package com.example.mydesign.data.datasource

import androidx.lifecycle.LiveData
import com.example.mydesign.api.API
import com.example.mydesign.api.ApiResponse
import com.example.mydesign.data.bean.MyResponse
import com.example.mydesign.data.bean.PositionResponseBean
import com.example.mydesign.data.bean.Resource
import com.example.mydesign.data.bean.entity.JobPositionEntity
import com.example.mydesign.data.db.UserDB
import com.example.mydesign.ext.isConnectedNetwork
import com.example.mydesign.utils.AppHelper
import com.example.mydesign.utils.ToastUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class PositionDetailDataSource @Inject constructor(
    private val api: API,
    private val db: UserDB
) {

    fun getDetail(scope: CoroutineScope, positionId: Int): LiveData<Resource<JobPositionEntity>> {
        return object :
            ScopeDataSource<MyResponse<PositionResponseBean>, JobPositionEntity>(scope) {
            override suspend fun loadData(): LiveData<ApiResponse<MyResponse<PositionResponseBean>>> {
                return api.getJobDetail(positionId)
            }

            override fun loadFromDb(): LiveData<JobPositionEntity> {
                return db.jobPositionDao().selectById(positionId)
            }

            override fun shouldFetch(data: JobPositionEntity?): Boolean {
                return AppHelper.mContext.isConnectedNetwork()
            }

            override suspend fun saveCallResult(item: MyResponse<PositionResponseBean>) {
                if (item.code == 200 && item.data != null) {
                    val jobPositionEntity =
                        JobPositionEntity.PositionResponseBean2JobPositionEntity(item.data)
                    db.jobPositionDao().insertJobPositionEntity(jobPositionEntity)
                } else {
                    scope.launch(Dispatchers.Main) {
                        ToastUtil.makeToast("数据异常：${item.description}")
                    }
                }
            }
        }.asLiveData()
    }


}