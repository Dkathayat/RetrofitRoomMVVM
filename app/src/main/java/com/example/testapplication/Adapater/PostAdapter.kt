package com.example.testapplication.Adapater


import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.testapplication.Data.MyPostItem
import com.example.testapplication.DetailsActivity
import com.example.testapplication.PostClickHandler
import com.example.testapplication.R


class PostAdapter(private val context: Context, var postList: ArrayList<MyPostItem>) : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    inner class PostViewHolder(itemView:View): RecyclerView.ViewHolder(itemView) {
        val mainRecylerViewtitle: TextView = itemView.findViewById(R.id.mainRecylerViewTitle)
        val mainRecylerViewId: TextView = itemView.findViewById(R.id.itemRecylerId)
    }



    fun updatePosts(newPostList: List<MyPostItem>) {
        postList.clear()
        postList.addAll(newPostList)
        notifyDataSetChanged()
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.main_recylerview_items,parent,false))

    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val pList = postList[position]
        holder.mainRecylerViewtitle.text = pList.title
        holder.mainRecylerViewId.text = pList.id.toString()

        holder.itemView.rootView.setOnClickListener {
           val intent = Intent(context,DetailsActivity::class.java)
            intent.putExtra("id",pList.id)
            intent.putExtra("userId",pList.userId)
            intent.putExtra("title",pList.title)
            intent.putExtra("userBody",pList.body)
            context.startActivity(intent)



        }

    }

    override fun getItemCount(): Int {
        return postList.size
    }

    interface PostClickDeleteInterface {
        fun onPostClicked(myPostItem: MyPostItem)

    }

}