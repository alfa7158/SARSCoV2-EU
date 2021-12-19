package com.example.covideu.model.covidNews.allCovidNews


import com.google.gson.annotations.SerializedName

data class getAllCoronaVirusNews(
    @SerializedName("news")
    val news: List<newsModel>
)