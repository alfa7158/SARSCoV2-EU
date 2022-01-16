package com.example.covideu.model.covidNews.allHealthNews


import com.google.gson.annotations.SerializedName

/**
 * This class is all health list news model
 *  */
data class getAllHealthNewsModel(
    @SerializedName("news")
    val news: List<AllHeathNewsModel>
)