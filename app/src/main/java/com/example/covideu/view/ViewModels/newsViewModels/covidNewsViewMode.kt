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



    val CovidLiveDataError = MutableLiveData<String?>()



    fun callAllCovidNews(page: Int?){

        viewModelScope.launch(Dispatchers.IO){
            val response = page?.let { apiRepo.getAllCovid19News(it) }
            try {
                if (response != null) {
                    if(response.isSuccessful){

                        response.body()?.run{
                            Log.d(TAG,this.toString())
                            Log.d(TAG,"${this.news}")
                            covidAllNewsLiveData.postValue(this.news)


                        }


                    }else{
                        CovidLiveDataError.postValue(response.message())


                    }
                }

            }catch (e: Exception){
                CovidLiveDataError.postValue(e.toString())



            }
        }
    }
}