package com.example.covideu.model.covidNews.allVaccineNews


import com.google.gson.annotations.SerializedName

data class getAllVaccineNewsModel(
    @SerializedName("news")
    val news: List<allVaccineNews>
)