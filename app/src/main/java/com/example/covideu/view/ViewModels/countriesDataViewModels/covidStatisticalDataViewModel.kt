package com.example.covideu.view.ViewModels.countriesDataViewModels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.covideu.model.get.all.covid.Countries.Statistical.getAllCovid19CountriesStatisticalDataItemModel
import com.example.covideu.model.getAllNorthernAmericanCountries.getAllNorthernAmericanCountriesModel
import com.example.covideu.model.getCountriesCovid.getOnlyCountriesCovid19DataModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

private const val TAG = "covidStatisticalDataVie"
class covidStatisticalDataViewModel:ViewModel() {

    private val apiRepo = ApiRepositoryCovidData.get()

    val covid19StatisticalLiveData = MutableLiveData<List<getAllCovid19CountriesStatisticalDataItemModel>>()


    val CovidLiveDataError = MutableLiveData<String?>()

    fun callCovidStatisticalData(){

        viewModelScope.launch(Dispatchers.IO){
            val response = apiRepo.getStatisticalData()
            try {
                if(response.isSuccessful){

                    response.body()?.run{
                        Log.d(TAG,this.toString())

                        covid19StatisticalLiveData.postValue(this)

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