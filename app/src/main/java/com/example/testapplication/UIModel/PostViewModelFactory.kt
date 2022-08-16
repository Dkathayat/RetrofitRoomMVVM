package com.example.testapplication.UIModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.testapplication.Repostory.PostRepository

class PostViewModelFactory (
    private val postRepository: PostRepository
        ): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PostViewModel(postRepository) as T
    }
}