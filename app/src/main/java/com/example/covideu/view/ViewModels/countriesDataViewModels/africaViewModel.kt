package com.example.covideu.view.ViewModels.countriesDataViewModels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.covideu.model.getAllAfricanCountries.getAllAfricanCountriesModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

private const val TAG = "africaViewModel"
class africaViewModel:ViewModel() {
    private val apiRepo = ApiRepositoryCovidData.get()
    val covid19AfricaLiveData = MutableLiveData<List<getAllAfricanCountriesModel>>()
    val CovidLiveDataError = MutableLiveData<String?>()

    fun callCovidDataForAfrica(){

        viewModelScope.launch(Dispatchers.IO){
            val response = apiRepo.getCovidDataForAfrica()
            try {
                if(response.isSuccessful){

                    response.body()?.run{
                        Log.d(TAG,this.toString())

                        covid19AfricaLiveData.postValue(this)

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