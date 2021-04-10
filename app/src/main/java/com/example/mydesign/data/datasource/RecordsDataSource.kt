package com.example.mydesign.data.datasource

import androidx.lifecycle.LiveData
import com.example.mydesign.api.API
import com.example.mydesign.api.ApiResponse
import com.example.mydesign.data.bean.MyResponse
import com.example.mydesign.data.bean.entity.RecordsEntity
import com.example.mydesign.data.db.UserDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.http.Query
import javax.inject.Inject

class RecordsDataSource @Inject constructor(
    private val api: API,
    private val db: UserDB
) {

    fun signUp(
        userAccountId: Int,
        jobId: Int,
        employerAccountId: Int
    ): LiveData<ApiResponse<MyResponse<RecordsEntity>>> {
        return api.signUp(userAccountId, jobId, employerAccountId);
    }

    fun saveRecordsDB(scope: CoroutineScope, recordsEntity: RecordsEntity) {
        scope.launch(Dispatchers.IO) {
            db.recordsDao().insertRecordsEntity(recordsEntity)
        }
    }
}