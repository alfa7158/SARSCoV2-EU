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

private const val TAG = "europViewModel"
class europViewModel:ViewModel() {

    private val apiRepo = ApiRepositoryCovidData.get()

    val covid19EuropeLiveData = MutableLiveData<List<getAllEuropeanCountriesModel>>()
    val covid19EuropeLiveDataDetails = MutableLiveData<getAllEuropeanCountriesModel>()

    val countryLiveDataSuccessful = MutableLiveData<String>()


    val CovidLiveDataError = MutableLiveData<String?>()


    fun callCovidDataForEurope(){

        viewModelScope.launch(Dispatchers.IO){
            val response = apiRepo.getCovidDataForEurope()
            try {
                if(response.isSuccessful){

                    response.body()?.run{
                        Log.d(TAG,this.toString())

                        covid19EuropeLiveData.postValue(this)
                        countryLiveDataSuccessful.postValue("Successful")
                    }


                }else{
                    CovidLiveDataError.postValue(response.message())


                }

            }catch (e: Exception){
                CovidLiveDataError.postValue(e.toString())



            }
        }
    }
}