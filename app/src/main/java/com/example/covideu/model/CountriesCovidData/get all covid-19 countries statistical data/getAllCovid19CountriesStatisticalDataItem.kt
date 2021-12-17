package com.example.covideu.model.get.all.covid.Countries.Statistical


import com.google.gson.annotations.SerializedName

data class getAllCovid19CountriesStatisticalDataItem(
    @SerializedName("ActiveCases")
    val activeCases: Int,
    @SerializedName("Case_Fatality_Rate")
    val caseFatalityRate: Double,
    @SerializedName("Continent")
    val continent: String,
    @SerializedName("Country")
    val country: String,
    @SerializedName("Deaths_1M_pop")
    val deaths1MPop: Double,
    @SerializedName("id")
    val id: String,
    @SerializedName("Infection_Risk")
    val infectionRisk: Int,
    @SerializedName("NewCases")
    val newCases: Int,
    @SerializedName("NewDeaths")
    val newDeaths: Int,
    @SerializedName("NewRecovered")
    val newRecovered: Int,
    @SerializedName("one_Caseevery_X_ppl")
    val oneCaseeveryXPpl: Int,
    @SerializedName("one_Deathevery_X_ppl")
    val oneDeatheveryXPpl: Int,
    @SerializedName("one_Testevery_X_ppl")
    val oneTesteveryXPpl: Int,
    @SerializedName("Population")
    val population: String,
    @SerializedName("rank")
    val rank: Int,
    @SerializedName("Recovery_Proporation")
    val recoveryProporation: Double,
    @SerializedName("Serious_Critical")
    val seriousCritical: Int,
    @SerializedName("Test_Percentage")
    val testPercentage: Int,
    @SerializedName("Tests_1M_Pop")
    val tests1MPop: Int,
    @SerializedName("ThreeLetterSymbol")
    val threeLetterSymbol: Any,
    @SerializedName("TotCases_1M_Pop")
    val totCases1MPop: Double,
    @SerializedName("TotalCases")
    val totalCases: Int,
    @SerializedName("TotalDeaths")
    val totalDeaths: Int,
    @SerializedName("TotalRecovered")
    val totalRecovered: String,
    @SerializedName("TotalTests")
    val totalTests: String,
    @SerializedName("TwoLetterSymbol")
    val twoLetterSymbol: Any
)