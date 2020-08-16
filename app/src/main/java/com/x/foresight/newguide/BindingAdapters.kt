package com.x.foresight.newguide

import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.x.foresight.newguide.adapters.NewsItemAdapter
import com.x.foresight.newguide.model.NewsArticle

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = it.toUri().buildUpon().scheme("https").build()

        Picasso.get().load(imgUri)
            .error(R.drawable.ic_broken_image)
            .placeholder(R.drawable.loading_animation)
            .into(imgView)
    }
}

@BindingAdapter("newsCaption")
fun showTitle(textView: TextView, title: String) {
    textView.text = title
}


@BindingAdapter("newsData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<NewsArticle>?) {
    val adapter = recyclerView.adapter as NewsItemAdapter

    adapter.submitList(data)
}
