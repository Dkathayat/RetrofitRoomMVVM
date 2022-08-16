package com.example.testapplication

import com.example.testapplication.Data.MyPostItem

interface PostClickHandler {

    fun clickedPostItem(myPostItem: MyPostItem)
}