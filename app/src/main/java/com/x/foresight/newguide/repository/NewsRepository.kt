package com.x.foresight.newguide.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.x.foresight.newguide.database.NewsDatabase
import com.x.foresight.newguide.model.NewsArticle
import com.x.foresight.newguide.model.NewsResponse
import com.x.foresight.newguide.network.Network
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class NewsRepository(private val database: NewsDatabase) {

    // The call on call only when this LiveData is being observed
    val articles: LiveData<List<NewsArticle>> = database.newsDao.getArticles()

    private val _status = MutableLiveData<NewsStatus>()
    val status: LiveData<NewsStatus> get() = _status

    suspend fun refreshData() {
        withContext(Dispatchers.IO) {
            try {
                // LOADING
                val response = Network.service.getHeadlines().await()

                // _status.value = NewsStatus.DONE

                // DONE
                // If the response is valid, the old data has to be dropped
                // For the new response to be inserted
                if(response.articles.isNotEmpty()) {
                    database.newsDao.deleteAll()
                }

                database.newsDao.insertAll(*response.articles.toTypedArray())
            } catch (e: Exception) {
                e.printStackTrace()
                // _status.value = NewsStatus.ERROR
            }
        }
    }

}


enum class NewsStatus{
    LOADING, DONE, ERROR
}
