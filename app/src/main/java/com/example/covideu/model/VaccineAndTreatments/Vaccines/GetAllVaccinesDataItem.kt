package com.example.covideu.model.VaccineAndTreatments.Vaccines


import com.google.gson.annotations.SerializedName

/**
 * This ,model is for all vaccine data
 */
data class getAllVaccinesDataItem(
    @SerializedName("category")
    val category: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("developerResearcher")
    val developerResearcher: String,
    @SerializedName("FDAApproved")
    val fDAApproved: String,
    @SerializedName("funder")
    val funder: String,
    @SerializedName("lastUpdated")
    val lastUpdated: String,
    @SerializedName("nextSteps")
    val nextSteps: String,
    @SerializedName("phase")
    val phase: String,
    @SerializedName("trimedCategory")
    val trimedCategory: String,
    @SerializedName("trimedName")
    val trimedName: String
)