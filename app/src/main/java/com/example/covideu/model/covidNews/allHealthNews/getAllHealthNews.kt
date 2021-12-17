package com.example.covideu.model.covidNews.allHealthNews


import com.google.gson.annotations.SerializedName

data class getAllHealthNews(
    @SerializedName("news")
    val news: List<New>
)