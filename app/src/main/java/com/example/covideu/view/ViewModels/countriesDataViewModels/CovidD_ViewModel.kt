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

class CovidD_ViewModel:ViewModel() {
//    private val apiRepo = ApiRepositoryCovidData.get()
//    val covidAllStatisticalLiveData = MutableLiveData<List<getAllCovid19CountriesStatisticalDataItemModel>>()
//    val covidAllCountriesWithCovidOnlyLiveData = MutableLiveData<List<getOnlyCountriesCovid19DataModel>>()
//    val covid19AsiaLiveData = MutableLiveData<List<getAll_AsianCountriesModel>>()
//    val covid19AfricaLiveData = MutableLiveData<List<getAllAfricanCountriesModel>>()
//    val covid19EuropeLiveData = MutableLiveData<List<getAllEuropeanCountriesModel>>()
//    val covid19NorthAmericaLiveData = MutableLiveData<List<getAllNorthernAmericanCountriesModel>>()
//    val covid19SouthAmericaLiveData = MutableLiveData<List<getAllSouthernAmericanCountriesModel>>()
//    val covid19AustralianAndOceaniaLiveData = MutableLiveData<List<getAllAustralianAndOceanianCountriesModel>>()
//    val covidWorldData = MutableLiveData<worldCovidCaesModelItem>()
//
//    val CovidLiveDataError = MutableLiveData<String?>()
//
//    fun callCovidStatisticalData(){
//
//        viewModelScope.launch(Dispatchers.IO){
//            val response = apiRepo.getStatisticalData()
//            try {
//                if(response.isSuccessful){
//
//                    response.body()?.run{
//                        Log.d(TAG,this.toString())
//
//                        covidAllStatisticalLiveData.postValue(this)
//
//                    }
//
//
//                }else{
//                    CovidLiveDataError.postValue(response.message())
//
//                }
//
//            }catch (e:Exception){
//                CovidLiveDataError.postValue(e.toString())
//
//
//
//            }
//        }
//    }
//
//    fun callCovidCountriesWithCovidOnly(){
//
//        viewModelScope.launch(Dispatchers.IO){
//            val response = apiRepo.getDataForCountriesWithCovid19()
//            try {
//                if(response.isSuccessful){
//
//                    response.body()?.run{
//                        Log.d(TAG,this.toString())
//
//                        covidAllCountriesWithCovidOnlyLiveData.postValue(this)
//
//                    }
//
//
//                }else{
//                    CovidLiveDataError.postValue(response.message())
//
//                }
//
//            }catch (e:Exception){
//                CovidLiveDataError.postValue(e.toString())
//
//
//
//            }
//        }
//    }
//
//    fun callCovidDataForAsia(){
//
//        viewModelScope.launch(Dispatchers.IO){
//            val response = apiRepo.getCovidDataForAsia()
//            try {
//                if(response.isSuccessful){
//
//                    response.body()?.run{
//                        Log.d(TAG,this.toString())
//
//                        covid19AsiaLiveData.postValue(this)
//
//                    }
//
//
//                }else{
//                    CovidLiveDataError.postValue(response.message())
//
//
//                }
//
//            }catch (e:Exception){
//                CovidLiveDataError.postValue(e.toString())
//
//
//
//            }
//        }
//    }
//    fun callCovidDataForAfrica(){
//
//        viewModelScope.launch(Dispatchers.IO){
//            val response = apiRepo.getCovidDataForAfrica()
//            try {
//                if(response.isSuccessful){
//
//                    response.body()?.run{
//                        Log.d(TAG,this.toString())
//
//                        covid19AfricaLiveData.postValue(this)
//
//                    }
//
//
//                }else{
//
//                    CovidLiveDataError.postValue(response.message())
//
//                }
//
//            }catch (e:Exception){
//                CovidLiveDataError.postValue(e.toString())
//
//
//
//            }
//        }
//    }
//
//    fun callCovidDataForEurope(){
//
//        viewModelScope.launch(Dispatchers.IO){
//            val response = apiRepo.getCovidDataForEurope()
//            try {
//                if(response.isSuccessful){
//
//                    response.body()?.run{
//                        Log.d(TAG,this.toString())
//
//                        covid19EuropeLiveData.postValue(this)
//
//                    }
//
//
//                }else{
//                    CovidLiveDataError.postValue(response.message())
//
//
//                }
//
//            }catch (e:Exception){
//                CovidLiveDataError.postValue(e.toString())
//
//
//
//            }
//        }
//    }
//
//    fun callCovidDataForNorthAmerican(){
//
//        viewModelScope.launch(Dispatchers.IO){
//            val response = apiRepo.getCovidDataForNorthAmerican()
//            try {
//                if(response.isSuccessful){
//
//                    response.body()?.run{
//                        Log.d(TAG,this.toString())
//
//                        covid19NorthAmericaLiveData.postValue(this)
//
//                    }
//
//
//                }else{
//                    CovidLiveDataError.postValue(response.message())
//
//
//                }
//
//            }catch (e:Exception){
//                CovidLiveDataError.postValue(e.toString())
//
//
//
//            }
//        }
//    }
//    fun callCovidDataForSouthAmerican(){
//
//        viewModelScope.launch(Dispatchers.IO){
//            val response = apiRepo.getCovidDataForSouthAmerican()
//            try {
//                if(response.isSuccessful){
//
//                    response.body()?.run{
//                        Log.d(TAG,this.toString())
//
//                        covid19SouthAmericaLiveData.postValue(this)
//
//                    }
//
//
//                }else{
//                    CovidLiveDataError.postValue(response.message())
//
//
//                }
//
//            }catch (e:Exception){
//                CovidLiveDataError.postValue(e.toString())
//
//
//
//            }
//        }
//    }
//
//
//    fun callCovidDataForAustralianAndOcean(){
//
//        viewModelScope.launch(Dispatchers.IO){
//            val response = apiRepo.getCovidDataForAustraliaAndOceania()
//            try {
//                if(response.isSuccessful){
//
//                    response.body()?.run{
//                        Log.d(TAG,this.toString())
//
//                        covid19AustralianAndOceaniaLiveData.postValue(this)
//
//                    }
//
//
//                }else{
//                    CovidLiveDataError.postValue(response.message())
//
//
//                }
//
//            }catch (e:Exception){
//                CovidLiveDataError.postValue(e.toString())
//
//
//
//            }
//        }
//    }
//
//    fun callCovidWorldData(){
//
//        viewModelScope.launch(Dispatchers.IO){
//            val response = apiRepo.getWorldData()
//            try {
//                if(response.isSuccessful){
//
//                    response.body()?.run{
//                        Log.d(TAG,this.toString())
//
//                        covidWorldData.postValue(this[0])
//
//                    }
//
//
//                }else{
//                    CovidLiveDataError.postValue(response.message())
//
//
//                }
//
//            }catch (e:Exception){
//                CovidLiveDataError.postValue(e.toString())
//
//
//
//            }
//        }
//    }


}