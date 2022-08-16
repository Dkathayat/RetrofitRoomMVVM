package com.example.testapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.testapplication.Data.MyPostItem
import com.example.testapplication.Repostory.PostRepository
import com.example.testapplication.UIModel.PostViewModel
import com.example.testapplication.UIModel.PostViewModelFactory
import com.example.testapplication.databinding.ActivityDetailsBinding
import com.example.testapplication.roomDB.PostDataBase
import java.util.*
import kotlin.collections.ArrayList

class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding
    private lateinit var viewModel: PostViewModel
    private lateinit var myPostItem: MyPostItem
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val postRepository = PostRepository(PostDataBase(this))
        val viewModelProviderFactory = PostViewModelFactory(postRepository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory)[PostViewModel::class.java]

        val getDataIntent = intent
        val id = getDataIntent.getStringExtra("id")
        val postid = getDataIntent.getStringExtra("userId")
        val posttitle = getDataIntent.getStringExtra("title")
        val postbody = getDataIntent.getStringExtra("userBody")

        binding.apply {
            postID.text = postid
            postTitle.text = posttitle.toString()
            postBody.text = postbody.toString()
        }

        binding.postDeleteBtn.setOnClickListener {

        }

    }
}