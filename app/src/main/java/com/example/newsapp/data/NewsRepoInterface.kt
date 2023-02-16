package com.akshay.newsapp.news.domain

import com.akshay.newsapp.news.api.NewsService
import com.example.newsapp.data.NewsArticle
import com.example.newsapp.data.base.RetrofitExecutor
import com.example.newsapp.data.model.Source
import com.example.newsapp.ui.Result
import javax.inject.Inject


interface NewsRepository {

    suspend fun getNewsFromWebservice(country: String): Result<List<NewsArticle>>
    suspend fun getSource():Result<List<Source>>
}

class DefaultNewsRepository @Inject constructor(
    private val newsService: NewsService,
    private val retrofitExecutor: RetrofitExecutor
) : NewsRepository {

    override suspend fun getNewsFromWebservice(country: String):Result<List<NewsArticle>> {

        return retrofitExecutor.makeRequest {newsService.getNews(country)}
    }

    override suspend fun getSource(): Result<List<Source>> {
       return retrofitExecutor.makeRequest(RetrofitExecutor.SOURCE) { newsService.getSource() }
    }
}

