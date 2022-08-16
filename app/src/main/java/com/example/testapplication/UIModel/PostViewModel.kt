package com.example.testapplication.UIModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testapplication.Data.MyPostItem
import com.example.testapplication.Network.NetworkResource
import com.example.testapplication.Repostory.PostRepository
import kotlinx.coroutines.*
import retrofit2.Response

class PostViewModel(private val postRepository: PostRepository) : ViewModel() {

     val postData: MutableLiveData<NetworkResource<List<MyPostItem>>> = MutableLiveData()

    init {
        getPostData()
    }

    private fun getPostData() = viewModelScope.launch {
        postData.postValue(NetworkResource.Loading())
        val response = postRepository.getPosts()
        postData.postValue(handlePostResponse(response))
    }

    private fun handlePostResponse(response: Response<List<MyPostItem>>): NetworkResource<List<MyPostItem>> {
        if (response.isSuccessful) {
            response.body()?.let {
                return NetworkResource.Success(it)
            }
        }
        return NetworkResource.Error(response.message())
    }
    fun savePostToDb(myPostItem: MyPostItem) = viewModelScope.launch {
        postRepository.upsert(myPostItem)
    }
    fun getSavedPost() = postRepository.getSavedPost()

    fun deletePost(myPostItem: MyPostItem) = viewModelScope.launch {
        postRepository.deletePost(myPostItem)
    }
}
