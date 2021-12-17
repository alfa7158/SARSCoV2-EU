package com.example.covideu.model.covidNews.allHealthNews


import com.google.gson.annotations.SerializedName

data class getAllHealthNewsModel(
    @SerializedName("news")
    val news: List<AllHeathNewsModel>
)