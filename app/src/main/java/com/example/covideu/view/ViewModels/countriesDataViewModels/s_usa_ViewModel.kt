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
 * This  South USA  data view model which is responsible for calling the african data from
 * the database
 */
private const val TAG = "s_usa_ViewModel"
class s_usa_ViewModel:ViewModel() {

    private val apiRepo = ApiRepositoryCovidData.get()
   
    val covid19SouthAmericaLiveData = MutableLiveData<List<getAllSouthernAmericanCountriesModel>>()
    val covid19SouthAmericaLiveDataDetails = MutableLiveData<getAllSouthernAmericanCountriesModel>()
    val countryLiveDataSuccessful = MutableLiveData<String>()
    val CovidLiveDataError = MutableLiveData<String?>()

    /**
     * The function below is responsible for getting all  South American countries data from the api
     */

    fun callCovidDataForSouthAmerican(){

        viewModelScope.launch(Dispatchers.IO){
            val response = apiRepo.getCovidDataForSouthAmerican()
            try {
                if(response.isSuccessful){

                    response.body()?.run{
                        Log.d(TAG,this.toString())

                        covid19SouthAmericaLiveData.postValue(this)
                        countryLiveDataSuccessful.postValue("successful")
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