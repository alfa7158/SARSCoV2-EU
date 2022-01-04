package com.example.covideu.view.ViewModels.newsViewModels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.covideu.model.covidNews.allCovidNews.newsModel
import com.example.covideu.model.covidNews.allVaccineNews.allVaccineNews
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

private const val TAG = "covidNewsViewMode"
class covidNewsViewMode:ViewModel() {



    private val apiRepo = ApiRepositoryCovidData.get()
    val covidAllNewsLiveData = MutableLiveData<List<newsModel>>()
    val covidAllNewsLiveDataDetails = MutableLiveData<newsModel>()
    var page:Int = 0
    var pages:Int = 1000

    val CovidLiveDataError = MutableLiveData<String?>()



    fun callAllCovidNews(){

        viewModelScope.launch(Dispatchers.IO){
            val response = apiRepo.getAllCovid19News(page)
            try {
                if(page<pages) {
                    Log.d(TAG,"thepageishere ${page.toString()}")

                    if (response.isSuccessful) {

                        response.body()?.run {
                            Log.d(TAG, this.toString())
                            Log.d(TAG, "${this.news}")
                            covidAllNewsLiveData.postValue(this.news)



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