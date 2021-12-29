package com.example.covideu.API

import com.example.covideu.model.VaccineAndTreatments.Treatment.getAllTreatmentsData
import com.example.covideu.model.VaccineAndTreatments.Treatment.getClinicalTreatments
import com.example.covideu.model.VaccineAndTreatments.Treatment.getFDA_Approvedtreatments
import com.example.covideu.model.VaccineAndTreatments.Vaccines.*
import com.example.covideu.model.covidNews.allCovidNews.getAllCoronaVirusNews
import com.example.covideu.model.covidNews.allHealthNews.getAllHealthNewsModel
import com.example.covideu.model.covidNews.allVaccineNews.getAllVaccineNewsModel
import com.example.covideu.model.get.all.covid.Countries.Statistical.getAllCovid19CountriesStatisticalDataItemModel
import com.example.covideu.model.getAllAfricanCountries.getAllAfricanCountriesModel
import com.example.covideu.model.getAllAsianCountries.getAll_AsianCountriesModel
import com.example.covideu.model.getAllAustralianAndOceaniancounties.getAllAustralianAndOceanianCountriesModel
import com.example.covideu.model.getAllEuropeanCountries.getAllEuropeanCountriesModel
import com.example.covideu.model.getAllNorthernAmericanCountries.getAllNorthernAmericanCountriesModel
import com.example.covideu.model.getAllSouthernAmericanCountries.getAllSouthernAmericanCountriesModel
import com.example.covideu.model.getCountriesCovid.getOnlyCountriesCovid19DataModel
import com.example.covideu.model.worldCovidCases.worldCovidCaesModelItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface CovidEu_API {

    /**The function below are for countries and world covid Data*/
    @Headers("x-rapidapi-host: vaccovid-coronavirus-vaccine-and-treatment-tracker.p.rapidapi.com","x-rapidapi-key: cba0ee63e3msh7d6dd8e84219c71p1713b9jsn45b1358b232c")
    @GET("/api/npm-covid-data/")
    suspend fun getCovid19StatisticalData(): Response<List<getAllCovid19CountriesStatisticalDataItemModel>>

    @Headers("x-rapidapi-host: vaccovid-coronavirus-vaccine-and-treatment-tracker.p.rapidapi.com","x-rapidapi-key: cba0ee63e3msh7d6dd8e84219c71p1713b9jsn45b1358b232c")
    @GET("/api/npm-covid-data/countries")
    suspend fun getDataForCountriesWithCovid19(): Response<List<getOnlyCountriesCovid19DataModel>>

    @Headers("x-rapidapi-host: vaccovid-coronavirus-vaccine-and-treatment-tracker.p.rapidapi.com","x-rapidapi-key: cba0ee63e3msh7d6dd8e84219c71p1713b9jsn45b1358b232c")
    @GET("/api/npm-covid-data/asia")
    suspend fun getCovidDataForAsia(): Response<List<getAll_AsianCountriesModel>>

    @Headers("x-rapidapi-host: vaccovid-coronavirus-vaccine-and-treatment-tracker.p.rapidapi.com","x-rapidapi-key: cba0ee63e3msh7d6dd8e84219c71p1713b9jsn45b1358b232c")
    @GET("/api/npm-covid-data/africa")
    suspend fun getCovidDataForAfrica(): Response<List<getAllAfricanCountriesModel>>

    @Headers("x-rapidapi-host: vaccovid-coronavirus-vaccine-and-treatment-tracker.p.rapidapi.com","x-rapidapi-key: cba0ee63e3msh7d6dd8e84219c71p1713b9jsn45b1358b232c")
    @GET("/api/npm-covid-data/europe")
    suspend fun getCovidDataForEurope(): Response<List<getAllEuropeanCountriesModel>>

    @Headers("x-rapidapi-host: vaccovid-coronavirus-vaccine-and-treatment-tracker.p.rapidapi.com","x-rapidapi-key: cba0ee63e3msh7d6dd8e84219c71p1713b9jsn45b1358b232c")
    @GET("/api/npm-covid-data/northamerica")
    suspend fun getCovidDataForNorthAmerican(): Response<List<getAllNorthernAmericanCountriesModel>>

    @Headers("x-rapidapi-host: vaccovid-coronavirus-vaccine-and-treatment-tracker.p.rapidapi.com","x-rapidapi-key: cba0ee63e3msh7d6dd8e84219c71p1713b9jsn45b1358b232c")
    @GET("/api/npm-covid-data/southamerica")
    suspend fun getCovidDataForSouthAmerican(): Response<List<getAllSouthernAmericanCountriesModel>>

    @Headers("x-rapidapi-host: vaccovid-coronavirus-vaccine-and-treatment-tracker.p.rapidapi.com","x-rapidapi-key: cba0ee63e3msh7d6dd8e84219c71p1713b9jsn45b1358b232c")
    @GET("/api/npm-covid-data/australia")
    suspend fun getCovidDataForAustraliaAndOceania(): Response<List<getAllAustralianAndOceanianCountriesModel>>

    @Headers("x-rapidapi-host: vaccovid-coronavirus-vaccine-and-treatment-tracker.p.rapidapi.com","x-rapidapi-key: cba0ee63e3msh7d6dd8e84219c71p1713b9jsn45b1358b232c")
    @GET("/api/npm-covid-data/world")
    suspend fun getWorldCovidData(): Response<List<worldCovidCaesModelItem>>
////////////////////////////////////////////////////////////////////////////////////////////////////


    /**The function below are for vaccine and treatment*/


    @Headers("x-rapidapi-host: vaccovid-coronavirus-vaccine-and-treatment-tracker.p.rapidapi.com","x-rapidapi-key: cba0ee63e3msh7d6dd8e84219c71p1713b9jsn45b1358b232c")
    @GET("api/vaccines/get-all-treatment")
    suspend fun getAllTreatmentData(): Response<List<getAllTreatmentsData>>

    @Headers("x-rapidapi-host: vaccovid-coronavirus-vaccine-and-treatment-tracker.p.rapidapi.com","x-rapidapi-key: cba0ee63e3msh7d6dd8e84219c71p1713b9jsn45b1358b232c")
    @GET("/api/vaccines/get-all-vaccines")
    suspend fun getAllVaccinesData(): Response<List<getAllVaccinesDataItem>>

    @Headers("x-rapidapi-host: vaccovid-coronavirus-vaccine-and-treatment-tracker.p.rapidapi.com","x-rapidapi-key: cba0ee63e3msh7d6dd8e84219c71p1713b9jsn45b1358b232c")
    @GET("/api/vaccines/get-all-treatment-clinical")
    suspend fun getAllClinicalTreatment(): Response<List<getClinicalTreatments>>

    @Headers("x-rapidapi-host: vaccovid-coronavirus-vaccine-and-treatment-tracker.p.rapidapi.com","x-rapidapi-key: cba0ee63e3msh7d6dd8e84219c71p1713b9jsn45b1358b232c")
    @GET("/api/vaccines/get-all-fda-approved-treatment")
    suspend fun getAllApprovedFDATreatmentData(): Response<List<getFDA_Approvedtreatments>>

    @Headers("x-rapidapi-host: vaccovid-coronavirus-vaccine-and-treatment-tracker.p.rapidapi.com","x-rapidapi-key: cba0ee63e3msh7d6dd8e84219c71p1713b9jsn45b1358b232c")
    @GET("/api/vaccines/get-fda-approved-vaccines")
    suspend fun getAllApprovedFDAVaccinesData(): Response<List<getFDA_ApprovedVaccines>>

    @Headers("x-rapidapi-host: vaccovid-coronavirus-vaccine-and-treatment-tracker.p.rapidapi.com","x-rapidapi-key: cba0ee63e3msh7d6dd8e84219c71p1713b9jsn45b1358b232c")
    @GET("/api/vaccines/get-all-vaccines-phase-i")
    suspend fun getPhaseOne(): Response<List<getPhase_one_vaccines>>

    @Headers("x-rapidapi-host: vaccovid-coronavirus-vaccine-and-treatment-tracker.p.rapidapi.com","x-rapidapi-key: cba0ee63e3msh7d6dd8e84219c71p1713b9jsn45b1358b232c")
    @GET("/api/vaccines/get-all-vaccines-phase-ii")
    suspend fun getPhaseTwo(): Response<List<getPhase_two_vaccines>>

    @Headers("x-rapidapi-host: vaccovid-coronavirus-vaccine-and-treatment-tracker.p.rapidapi.com","x-rapidapi-key: cba0ee63e3msh7d6dd8e84219c71p1713b9jsn45b1358b232c")
    @GET("/api/vaccines/get-all-vaccines-phase-iii")
    suspend fun getPhaseThree(): Response<List<getPhase_three_vaccines>>

    @Headers("x-rapidapi-host: vaccovid-coronavirus-vaccine-and-treatment-tracker.p.rapidapi.com","x-rapidapi-key: cba0ee63e3msh7d6dd8e84219c71p1713b9jsn45b1358b232c")
    @GET("/api/vaccines/get-all-vaccines-phase-iv")
    suspend fun getPhaseFour(): Response<List<getPhase_four_vaccines>>


///////////////////////////////////////////////////////////////////////////////////////////////////


    /**The function below are for Covid and public health news*/

    @Headers("x-rapidapi-host: vaccovid-coronavirus-vaccine-and-treatment-tracker.p.rapidapi.com","x-rapidapi-key: cba0ee63e3msh7d6dd8e84219c71p1713b9jsn45b1358b232c")
    @GET("/api/news/get-coronavirus-news/{page}")
    suspend fun getAllCovid19News(@Path("page") page: Int): Response<getAllCoronaVirusNews>


    @Headers("x-rapidapi-host: vaccovid-coronavirus-vaccine-and-treatment-tracker.p.rapidapi.com","x-rapidapi-key: cba0ee63e3msh7d6dd8e84219c71p1713b9jsn45b1358b232c")
    @GET("/api/news/get-health-news/{page}")
    suspend fun getAllHealthNews(@Path("page") page: Int): Response<getAllHealthNewsModel>

    @Headers("x-rapidapi-host: vaccovid-coronavirus-vaccine-and-treatment-tracker.p.rapidapi.com","x-rapidapi-key: cba0ee63e3msh7d6dd8e84219c71p1713b9jsn45b1358b232c")
    @GET("/api/news/get-health-news/{page}")
    suspend fun getAllVaccineNews(@Path("page")page:Int): Response<getAllVaccineNewsModel>





















}