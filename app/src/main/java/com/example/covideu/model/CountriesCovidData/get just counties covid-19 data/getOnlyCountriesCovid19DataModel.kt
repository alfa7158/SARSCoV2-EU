package com.example.covideu.model.getCountriesCovid


import com.google.gson.annotations.SerializedName

data class getOnlyCountriesCovid19DataModel(
    @SerializedName("ActiveCases")
    val activeCases: Int,
    @SerializedName("Case_Fatality_Rate")
    val caseFatalityRate: Double,
    @SerializedName("Country")
    val country: String,
    @SerializedName("Infection_Risk")
    val infectionRisk: Double,
    @SerializedName("NewCases")
    val newCases: Int,
    @SerializedName("NewDeaths")
    val newDeaths: Int,
    @SerializedName("NewRecovered")
    val newRecovered: Int,
    @SerializedName("Population")
    val population: Int,
    @SerializedName("Recovery_Proporation")
    val recoveryProporation: Double,
    @SerializedName("Serious_Critical")
    val seriousCritical: Int,
    @SerializedName("Test_Percentage")
    val testPercentage: Double,
    @SerializedName("ThreeLetterSymbol")
    val threeLetterSymbol: String,
    @SerializedName("TotalCases")
    val totalCases: Int,
    @SerializedName("TotalDeaths")
    val totalDeaths: Int,
    @SerializedName("TotalRecovered")
    val totalRecovered: Int,
    @SerializedName("TotalTests")
    val totalTests: Int,
    @SerializedName("TwoLetterSymbol")
    val twoLetterSymbol: String
)