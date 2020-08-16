package com.x.foresight.newguide.model

data class NewsResponse(
    var status: String,
    var totalResults: Int,
    var articles: List<NewsArticle>
)