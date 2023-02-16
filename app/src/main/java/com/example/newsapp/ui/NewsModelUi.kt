package com.example.newsapp.ui

import com.example.newsapp.data.NewsArticle

class NewsModelUi(
    val itemsList: List<NewsArticle>
) {
    companion object {
        fun toUiModel(newsResponse: ArrayList<NewsArticle>): NewsModelUi {
            return NewsModelUi(
                newsResponse
            )
        }
    }
}