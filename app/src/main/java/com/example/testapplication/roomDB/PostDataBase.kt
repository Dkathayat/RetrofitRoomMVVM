package com.example.testapplication.roomDB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.testapplication.Data.MyPostItem

@Database(
    entities = [MyPostItem::class],
    version = 1
)
abstract class PostDataBase : RoomDatabase() {

    abstract fun getPostDao(): PostDao

    companion object {
        @Volatile
        private var instance: PostDataBase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also { instance = it}
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                PostDataBase::class.java,
                "post_db.db"
            ).build()
    }
}