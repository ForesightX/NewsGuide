package com.x.foresight.newguide.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*
import com.x.foresight.newguide.model.NewsArticle

@Dao
interface NewsDao {

    @Query("SELECT * FROM NewsArticle")
    fun getArticles() : LiveData<List<NewsArticle>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg newsArticle: NewsArticle)

    @Query("DELETE FROM NewsArticle")
    fun deleteAll()
}

@Database(entities = [NewsArticle::class], version = 1)
abstract class NewsDatabase : RoomDatabase() {
    abstract val newsDao: NewsDao
}

private lateinit var INSTANCE: NewsDatabase

fun getDatabase(context: Context) : NewsDatabase {
    synchronized(NewsDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                NewsDatabase::class.java,
                "news_database"
            ).build()
        }
    }
    return INSTANCE
}