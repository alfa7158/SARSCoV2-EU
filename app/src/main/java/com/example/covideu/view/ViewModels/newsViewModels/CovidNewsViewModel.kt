package com.example.covideu.view.ViewModels.newsViewModels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.covideu.model.covidNews.allCovidNews.newsModel
import com.example.covideu.model.covidNews.allHealthNews.AllHeathNewsModel
import com.example.covideu.model.covidNews.allVaccineNews.allVaccineNews
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

private const val TAG = "CovidNewsViewModel"
class CovidNewsViewModel:ViewModel() {

//    private val apiRepo = ApiRepositoryCovidData.get()
//    val covidAllNewsLiveData = MutableLiveData<List<newsModel>>()
//    val covidAllHeathLiveData = MutableLiveData<List<AllHeathNewsModel>>()
//    val covid19VaccineLiveData = MutableLiveData<List<allVaccineNews>>()
//
//    val CovidLiveDataError = MutableLiveData<String?>()


//    fun callAllCovidNews(page:String){
//
//        viewModelScope.launch(Dispatchers.IO){
//            val response = apiRepo.getAllCovid19News(page)
//            try {
//                if(response.isSuccessful){
//
//                    response.body()?.run{
//                        Log.d(TAG,this.toString())
//                        Log.d(TAG,"${this.news}")
//                        covidAllNewsLiveData.postValue(this.news)
//
//
//
//                    }
//
//
//                }else{
//                    CovidLiveDataError.postValue(response.message())
//
//
//                }
//
//            }catch (e: Exception){
//                CovidLiveDataError.postValue(e.toString())
//
//
//
//            }
//        }
//    }

//    fun callAllHealthNews(page: String){
//
//        viewModelScope.launch(Dispatchers.IO){
//            val response = apiRepo.getAllHealthNews(page)
//            try {
//                if(response.isSuccessful){
//
//                    response.body()?.run{
//                        Log.d(TAG,this.toString())
//
//                        covidAllHeathLiveData.postValue(this)
//
//                    }
//
//
//                }else{
//                    CovidLiveDataError.postValue(response.message())
//
//
//                }
//
//            }catch (e: Exception){
//                CovidLiveDataError.postValue(e.toString())
//
//
//
//            }
//        }
//    }
//
//    fun callAllVaccineNews(page:String){
//
//        viewModelScope.launch(Dispatchers.IO){
//            val response = apiRepo.getAllVaccineNews(page)
//            try {
//                if(response.isSuccessful){
//
//                    response.body()?.run{
//                        Log.d(TAG,this.toString())
//
//                        covid19VaccineLiveData.postValue(this)
//
//                    }
//
//
//                }else{
//                    CovidLiveDataError.postValue(response.message())
//
//
//                }
//
//            }catch (e: Exception){
//                CovidLiveDataError.postValue(e.toString())
//
//
//
//            }
//        }
//    }
}