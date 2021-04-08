package com.example.mydesign.data.datasource

import androidx.lifecycle.LiveData
import com.example.mydesign.api.API
import com.example.mydesign.api.ApiResponse
import com.example.mydesign.data.bean.MyResponse
import com.example.mydesign.data.bean.PositionResponseBean
import com.example.mydesign.data.bean.Resource
import com.example.mydesign.data.bean.entity.JobPositionEntity
import com.example.mydesign.data.bean.entity.UserAccountEntity
import com.example.mydesign.data.db.UserDB
import com.example.mydesign.ext.isConnectedNetwork
import com.example.mydesign.utils.AppHelper
import com.example.mydesign.utils.ToastUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomePositionDataSource @Inject constructor(
    private val api: API,
    private val db: UserDB
) {

    fun getJobList(
        scope: CoroutineScope,
        type: String? = null
    ): LiveData<Resource<List<JobPositionEntity>>> {
        return object :
            ScopeDataSource<MyResponse<List<PositionResponseBean>>, List<JobPositionEntity>>(scope) {
            override suspend fun loadData(): LiveData<ApiResponse<MyResponse<List<PositionResponseBean>>>> {
                return api.getJobList(type)
            }

            override fun loadFromDb(): LiveData<List<JobPositionEntity>> {
                return if (type == null) {
                    db.jobPositionDao().selectAll()
                } else {
                    db.jobPositionDao().selectAll(type)
                }
            }

            override fun shouldFetch(data: List<JobPositionEntity>?): Boolean {
                return AppHelper.mContext.isConnectedNetwork()
            }

            override suspend fun saveCallResult(item: MyResponse<List<PositionResponseBean>>) {
                if (item.code == 200 && item.data != null) {
                    val list = ArrayList<JobPositionEntity>()
                    item.data.map {
                        val jobPositionEntity =
                            JobPositionEntity.PositionResponseBean2JobPositionEntity(it)
                        list.add(jobPositionEntity)
                    }
                    db.jobPositionDao().insertJobPositionEntity(list)
                } else {
                    scope.launch(Dispatchers.Main) {
                        ToastUtil.makeToast("数据异常：${item.description}")
                    }
                }
            }

        }.asLiveData()
    }
}