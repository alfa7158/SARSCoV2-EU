package com.example.covideu.model.covidNews.allCovidNews


import com.google.gson.annotations.SerializedName

/**
 * This model is the list of news model for covid-19 news
 */
data class getAllCoronaVirusNews(
    @SerializedName("news")
    val news: List<newsModel>
)