package com.example.testapplication.roomDB

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.testapplication.Data.MyPostItem

@Dao
interface PostDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(myPostItem: com.example.testapplication.Data.MyPostItem): Long

    @Query("SELECT * FROM posts")
    fun getAllPost(): LiveData<List<MyPostItem>>

    @Delete
    suspend fun deletePost(myPostItem: MyPostItem)
}