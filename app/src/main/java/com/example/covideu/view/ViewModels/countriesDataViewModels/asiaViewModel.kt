package com.example.covideu.view.ViewModels.countriesDataViewModels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.covideu.model.getAllAfricanCountries.getAllAfricanCountriesModel
import com.example.covideu.model.getAllAsianCountries.getAll_AsianCountriesModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
/**
 * This Asian countries data view model which is responsible for calling the african data from
 * the database
 */
private const val TAG = "asiaViewModel"
class asiaViewModel:ViewModel() {

    private val apiRepo = ApiRepositoryCovidData.get()
    val covid19AsiaLiveData = MutableLiveData<List<getAll_AsianCountriesModel>>()
    val covid19AsiaLiveDataDetails = MutableLiveData<getAll_AsianCountriesModel>()
    val CovidLiveDataError = MutableLiveData<String?>()
    val countryLiveDataSuccessful = MutableLiveData<String>()
    /**
     * The function below is responsible for getting all Asian countries data from the api
     */
    fun callCovidDataForAsia(){

        viewModelScope.launch(Dispatchers.IO){
            val response = apiRepo.getCovidDataForAsia()
            try {
                if(response.isSuccessful){

                    response.body()?.run{
                        Log.d(TAG,this.toString())

                        covid19AsiaLiveData.postValue(this)
                        countryLiveDataSuccessful.postValue("successful")
                    }


                }else{
                    CovidLiveDataError.postValue(response.message())


                }

            }catch (e:Exception){
                CovidLiveDataError.postValue(e.toString())



            }
        }
    }
}