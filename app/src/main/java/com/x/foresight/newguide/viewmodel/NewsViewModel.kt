package com.x.foresight.newguide.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.x.foresight.newguide.database.NewsDatabase
import com.x.foresight.newguide.database.getDatabase
import com.x.foresight.newguide.model.NewsArticle
import com.x.foresight.newguide.repository.NewsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class NewsViewModel(application: Application) : AndroidViewModel(application) {

    // LiveData for observation db
    val articles: LiveData<List<NewsArticle>>

    private val viewModelJob = SupervisorJob()

    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val database: NewsDatabase = getDatabase(application)
    private val newsRepository: NewsRepository = NewsRepository(database)

    init {
        articles = newsRepository.articles

        viewModelScope.launch {
            newsRepository.refreshData()
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    /**
     * Factory for constructing NewsViewModel with parameter
     */
    class Factory(private val application: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(NewsViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return NewsViewModel(application) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}

