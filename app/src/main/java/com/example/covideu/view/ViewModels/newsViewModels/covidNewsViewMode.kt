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
/**
 * This  all covid news  data view model which is responsible for calling the covid data from
 * the database
 */
private const val TAG = "covidNewsViewMode"
class covidNewsViewMode:ViewModel() {



    private val apiRepo = ApiRepositoryCovidData.get()
    val covidAllNewsLiveData = MutableLiveData<List<newsModel>>()
    val covidAllNewsLiveDataDetails = MutableLiveData<newsModel>()
    var page:Int = 0
    var pages:Int = 1000
    val newsLiveDataSuccessful = MutableLiveData<String>()

    val CovidLiveDataError = MutableLiveData<String?>()

    /**
     * The function below is responsible for getting all covid news data from the api as well as
     * manging the number of pages we get when calling it
     */

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
                            newsLiveDataSuccessful.postValue("successful")



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