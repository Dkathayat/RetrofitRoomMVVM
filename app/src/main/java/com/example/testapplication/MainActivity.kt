package com.example.testapplication


import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testapplication.Adapater.PostAdapter
import com.example.testapplication.Network.NetworkResource
import com.example.testapplication.Repostory.PostRepository
import com.example.testapplication.UIModel.PostViewModel
import com.example.testapplication.UIModel.PostViewModelFactory
import com.example.testapplication.databinding.ActivityMainBinding
import com.example.testapplication.roomDB.PostDataBase


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: PostViewModel

    private val postListAdapter: PostAdapter = PostAdapter(this, arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val postRepository = PostRepository(PostDataBase(this))
        val viewModelProviderFactory = PostViewModelFactory(postRepository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory)[PostViewModel::class.java]
        updateRecylerView()



        viewModel.postData.observe(this, Observer { response ->
            when (response) {
                is NetworkResource.Success -> {
                    binding.mainActivityProgressBar.visibility = View.GONE
                    response.data?.let { postResponse ->
                        postListAdapter.updatePosts(postResponse)


                    }
                }
                is NetworkResource.Error -> {
                    binding.mainActivityProgressBar.visibility = View.GONE
                    response.message?.let { message ->
                        Log.e(TAG, "An error occured: $message")
                    }
                }
                is NetworkResource.Loading -> {
                    binding.mainActivityProgressBar.visibility = View.VISIBLE
                }
            }
        })
    }

    private fun updateRecylerView() {

        binding.mainActivityRecylerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = postListAdapter

        }

    }
}