package com.example.covideu.model.covidNews.allVaccineNews


import com.google.gson.annotations.SerializedName

/**
 * This class is all vaccine news model
 */
data class allVaccineNews(
    @SerializedName("content")
    val content: String,
    @SerializedName("imageFileName")
    val imageFileName: String,
    @SerializedName("imageInLocalStorage")
    val imageInLocalStorage: String,
    @SerializedName("link")
    val link: String,
    @SerializedName("news_id")
    val newsId: String,
    @SerializedName("pubDate")
    val pubDate: String,
    @SerializedName("reference")
    val reference: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("urlToImage")
    val urlToImage: String
)