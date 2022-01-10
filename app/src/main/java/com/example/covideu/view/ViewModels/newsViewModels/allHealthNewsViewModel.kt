package com.example.covideu.view.ViewModels.newsViewModels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.covideu.model.covidNews.allHealthNews.AllHeathNewsModel
import com.example.covideu.model.covidNews.allVaccineNews.allVaccineNews
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

private const val TAG = "allHealthNews"
class allHealthNewsViewModel:ViewModel() {

    var page: Int = 0
    var pages: Int = 1000
    private val apiRepo = ApiRepositoryCovidData.get()
    val covidAllHeathLiveData = MutableLiveData<List<AllHeathNewsModel>>()
    val covidAllHeathLiveDataDetails = MutableLiveData<AllHeathNewsModel>()
    val newsLiveDataSuccessful = MutableLiveData<String>()

    val CovidLiveDataError = MutableLiveData<String?>()


    fun callAllHealthNews() {

        viewModelScope.launch(Dispatchers.IO) {
            val response = apiRepo.getAllHealthNews(page)
            try {
                if (page < pages) {
                    Log.d(TAG, "thepageishere ${page.toString()}")

                    if (response.isSuccessful) {

                        response.body()?.run {
                            Log.d(TAG, this.toString())
                            Log.d(TAG, "${this.news}")
                            covidAllHeathLiveData.postValue(this.news)
                            newsLiveDataSuccessful.postValue("successful")

                        }
                        page++


                    } else {
                        CovidLiveDataError.postValue(response.message())


                    }
                }
            } catch (e: Exception) {
                CovidLiveDataError.postValue(e.toString())


            }
        }

    }
}