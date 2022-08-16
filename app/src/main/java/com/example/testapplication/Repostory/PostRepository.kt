package com.example.testapplication.Repostory

import com.example.testapplication.Data.MyPostItem
import com.example.testapplication.Network.PostRetrofitService
import com.example.testapplication.Network.RetrofitApi
import com.example.testapplication.roomDB.PostDataBase

class PostRepository( val db: PostDataBase) {

    suspend fun getPosts() = PostRetrofitService.api.getPost()

    suspend fun upsert(myPostItem: MyPostItem) = db.getPostDao().upsert(myPostItem)

    fun getSavedPost() = db.getPostDao().getAllPost()

    suspend fun deletePost(myPostItem: MyPostItem) = db.getPostDao().deletePost(myPostItem)
}