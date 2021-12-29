package com.example.covideu.view.ViewModels.newsViewModels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.covideu.model.covidNews.allVaccineNews.allVaccineNews
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

private const val TAG = "allVaccineNews"
class allVaccineNewsViewModel:ViewModel() {
    private val apiRepo = ApiRepositoryCovidData.get()

    val covid19VaccineLiveData = MutableLiveData<List<allVaccineNews>>()
    val covid19VaccineLiveDatDetails = MutableLiveData<allVaccineNews>()


    val CovidLiveDataError = MutableLiveData<String?>()

    fun callAllVaccineNews(page: Int){

        viewModelScope.launch(Dispatchers.IO){
            val response = apiRepo.getAllVaccineNews(page)
            try {
                if(response.isSuccessful){

                    response.body()?.run{
                        Log.d(TAG,this.toString())

                        covid19VaccineLiveData.postValue(this.news)

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