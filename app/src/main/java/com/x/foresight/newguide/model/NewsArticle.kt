package com.x.foresight.newguide.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NewsArticle(
    var author: String?,
    var title: String,
    @PrimaryKey var url: String,
    var urlToImage: String?
)