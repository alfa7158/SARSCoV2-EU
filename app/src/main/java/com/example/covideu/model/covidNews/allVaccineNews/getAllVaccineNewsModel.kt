package com.example.covideu.model.covidNews.allVaccineNews


import com.google.gson.annotations.SerializedName
/**
 * This class is all vaccine list news model
 */
data class getAllVaccineNewsModel(
    @SerializedName("news")
    val news: List<allVaccineNews>
)