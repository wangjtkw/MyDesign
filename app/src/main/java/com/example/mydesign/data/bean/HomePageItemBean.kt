package com.example.mydesign.data.bean

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "test")
data class HomePageItemBean(
    var age: Int
) {
    @PrimaryKey(autoGenerate = true)
    var key = 0
}

