package com.example.newsapp.ui

import com.example.newsapp.data.NewsArticle
import com.example.newsapp.data.model.Source

class SourceModelUi (val itemsList: List<Source>
) {
    companion object {
        fun toUiModel(newsResponse: ArrayList<Source>):SourceModelUi{
            return SourceModelUi(
                newsResponse
            )
        }
    }
}