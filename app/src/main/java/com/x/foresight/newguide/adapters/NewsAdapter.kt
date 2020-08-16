package com.x.foresight.newguide.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.x.foresight.newguide.R
import com.x.foresight.newguide.databinding.ItemNewsLayoutBinding
import com.x.foresight.newguide.model.NewsArticle

/*class NewsAdapter(private var articles: List<NewsArticle>)
    : RecyclerView.Adapter<NewsItemViewHolder>() {

    init {
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_news_layout, parent, false)
        return NewsItemViewHolder(view)
    }

    override fun getItemCount() = articles.size

    override fun onBindViewHolder(holder: NewsItemViewHolder, position: Int) {
        val article = articles[position]

        holder.tvNewsTitle.text = article.title

        val urlToImage: String? = article.urlToImage

        urlToImage?.apply {
            if (this != "") {
                Picasso.get().load(this).into(holder.ivNewsImageView)
            }
        }
    }
}

class NewsItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val ivNewsImageView: ImageView = itemView.findViewById(R.id.ivNewsImage)
    val tvNewsTitle: TextView = itemView.findViewById(R.id.tvNewsTitle)
}*/

class NewsItemAdapter : ListAdapter<NewsArticle, NewsItemAdapter.NewsViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<NewsArticle>() {
        override fun areItemsTheSame(oldItem: NewsArticle, newItem: NewsArticle): Boolean {
            return oldItem.url === newItem.url
        }

        override fun areContentsTheSame(oldItem: NewsArticle, newItem: NewsArticle): Boolean {
            return oldItem == newItem
        }


    }

    class NewsViewHolder private constructor(private var binding: ItemNewsLayoutBinding)
        : RecyclerView.ViewHolder(binding.root) {
        fun bind(newsArticle: NewsArticle) {
            binding.newsArticle = newsArticle
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): NewsViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemNewsLayoutBinding.inflate(layoutInflater, parent, false)
                return NewsViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val newsArticle = getItem(position)
        holder.bind(newsArticle)
    }

}
