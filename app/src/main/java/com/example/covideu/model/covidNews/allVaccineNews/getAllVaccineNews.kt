package com.example.covideu.model.covidNews.allVaccineNews


import com.google.gson.annotations.SerializedName

data class getAllVaccineNews(
    @SerializedName("news")
    val news: List<New>
)