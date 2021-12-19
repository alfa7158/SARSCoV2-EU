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

private const val TAG = "asiaViewModel"
class asiaViewModel:ViewModel() {

    private val apiRepo = ApiRepositoryCovidData.get()
    val covid19AsiaLiveData = MutableLiveData<List<getAll_AsianCountriesModel>>()
    val CovidLiveDataError = MutableLiveData<String?>()

    fun callCovidDataForAsia(){

        viewModelScope.launch(Dispatchers.IO){
            val response = apiRepo.getCovidDataForAsia()
            try {
                if(response.isSuccessful){

                    response.body()?.run{
                        Log.d(TAG,this.toString())

                        covid19AsiaLiveData.postValue(this)

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