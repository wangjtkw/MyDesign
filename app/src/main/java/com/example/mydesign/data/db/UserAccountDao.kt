package com.example.mydesign.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mydesign.data.bean.UserAccountBean

@Dao
interface UserAccountDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserAccount(userAccountBean: UserAccountBean)

    @Query("SELECT * FROM user_account_bean WHERE usersAccountAccount = :account")
    fun selectByAccount(account: String): LiveData<UserAccountBean>

    @Query("DELETE FROM user_account_bean WHERE usersAccountAccount = :account")
    fun deleteByAccount(account: String)
}