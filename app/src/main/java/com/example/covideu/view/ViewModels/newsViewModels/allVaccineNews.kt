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
    var page:Int = 0
    var pages:Int = 1000
    val covid19VaccineLiveData = MutableLiveData<List<allVaccineNews>>()
    val covid19VaccineLiveDatDetails = MutableLiveData<allVaccineNews>()


    val CovidLiveDataError = MutableLiveData<String?>()

    fun callAllVaccineNews(){

        viewModelScope.launch(Dispatchers.IO){
            val response = apiRepo.getAllVaccineNews(page)
            try {
                if(page<pages) {
                    Log.d(TAG,"thepageishere ${page.toString()}")

                    if (response.isSuccessful) {

                        response.body()?.run {
                            Log.d(TAG, this.toString())
                            Log.d(TAG, "${this.news}")
                            covid19VaccineLiveData.postValue(this.news)



                        }
                        page++


                    } else {
                        CovidLiveDataError.postValue(response.message())


                    }
                }
            }catch (e: Exception){
                CovidLiveDataError.postValue(e.toString())



            }
        }
    }
}