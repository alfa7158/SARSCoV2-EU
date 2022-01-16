package com.example.covideu.view.ViewModels.countriesDataViewModels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.covideu.model.get.all.covid.Countries.Statistical.getAllCovid19CountriesStatisticalDataItemModel
import com.example.covideu.model.getAllAfricanCountries.getAllAfricanCountriesModel
import com.example.covideu.model.getAllAsianCountries.getAll_AsianCountriesModel
import com.example.covideu.model.getAllAustralianAndOceaniancounties.getAllAustralianAndOceanianCountriesModel
import com.example.covideu.model.getAllEuropeanCountries.getAllEuropeanCountriesModel
import com.example.covideu.model.getAllNorthernAmericanCountries.getAllNorthernAmericanCountriesModel
import com.example.covideu.model.getAllSouthernAmericanCountries.getAllSouthernAmericanCountriesModel
import com.example.covideu.model.getCountriesCovid.getOnlyCountriesCovid19DataModel
import com.example.covideu.model.worldCovidCases.worldCovidCaesModelItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
/**
 * This Australia and Oceania countries data view model which is responsible for calling the african data from
 * the database
 */
private const val TAG = "auViewModel"
class auViewModel:ViewModel() {
    private val apiRepo = ApiRepositoryCovidData.get()

    val covid19AustralianAndOceaniaLiveData = MutableLiveData<List<getAllAustralianAndOceanianCountriesModel>>()
    val covid19AustralianAndOceaniaLiveDataDetails = MutableLiveData<getAllAustralianAndOceanianCountriesModel>()
    val countryLiveDataSuccessful = MutableLiveData<String>()
    val countryLiveDataError = MutableLiveData<String?>()

    /**
     * The function below is responsible for getting all  Australia and Oceania countries data from the api
     */
    fun callCovidDataForAustralianAndOcean(){

        viewModelScope.launch(Dispatchers.IO){
            val response = apiRepo.getCovidDataForAustraliaAndOceania()
            try {
                if(response.isSuccessful){

                    response.body()?.run{
                        Log.d(TAG,this.toString())

                        covid19AustralianAndOceaniaLiveData.postValue(this)
                        countryLiveDataSuccessful.postValue("successful")
                    }


                }else{
                    countryLiveDataError.postValue(response.message())


                }

            }catch (e: Exception){
                countryLiveDataError.postValue(e.toString())



            }
        }
    }
}