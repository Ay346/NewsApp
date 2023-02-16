package com.example.newsapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.data.NewsArticle
import com.example.newsapp.R
import com.squareup.picasso.Picasso

internal class NewsAdapter(
    private var itemsList: ArrayList<NewsArticle>,
    private val clickAction : (NewsArticle)->(Unit)
) :
    RecyclerView.Adapter<NewsAdapter.MyViewHolder>() {
    internal inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view){
        var image: ImageView = view.findViewById(R.id.newsImage)
        var author: TextView = view.findViewById(R.id.newsAuthor)
        var titleTextView: TextView = view.findViewById(R.id.newsTitle)
        var publishedAtTextView: TextView = view.findViewById(R.id.newsPublishedAt)
    }

    @NonNull
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_news_article, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = itemsList[position]
        // picasso for image
        Picasso.with(holder.image.context)
            .load(item.urlToImage)
            .into(holder.image)
        holder.author.text = item.author
        holder.titleTextView.text = item.title
        holder.publishedAtTextView.text = item.publishedAt
        holder.itemView.setOnClickListener{clickAction(item)}
    }

    override fun getItemCount(): Int {
        return itemsList.size
    }
}