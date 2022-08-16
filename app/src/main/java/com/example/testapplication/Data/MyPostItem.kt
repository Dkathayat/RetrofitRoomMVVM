package com.example.testapplication.Data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Posts")
data class MyPostItem(
    val body: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val title: String,
    val userId: Int
)