package com.example.newsapp.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Describes the response from news service API.
 */
data class NewsResponse(
    @SerializedName("articles")
    val articles: List<NewsArticle> ,
    val status: String
)

@Parcelize
data class NewsArticle(

    @SerializedName("author")
    val author: String? = null,


    @SerializedName("title")
    val title: String? = null,


    @SerializedName("url")
    val url: String? = null,


    @SerializedName("urlToImage")
    val urlToImage: String? = null,

    @SerializedName("publishedAt")
    val publishedAt: String? = null,

    @SerializedName("content")
    val content: String? = null,

    @SerializedName("description")
    val description: String? = null
) : Parcelable {

}