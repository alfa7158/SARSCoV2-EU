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


    private val apiRepo = ApiRepositoryCovidData.get()
    val covidAllHeathLiveData = MutableLiveData<List<AllHeathNewsModel>>()
    val covidAllHeathLiveDataDetails = MutableLiveData<AllHeathNewsModel>()

    val CovidLiveDataError = MutableLiveData<String?>()



    fun callAllHealthNews(page: Int){

        viewModelScope.launch(Dispatchers.IO){
            val response = page.let { apiRepo.getAllHealthNews(it) }

            try {
                if(response.isSuccessful){

                    response.body()?.run{
                        Log.d(TAG,this.toString())

                        covidAllHeathLiveData.postValue(this.news)

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