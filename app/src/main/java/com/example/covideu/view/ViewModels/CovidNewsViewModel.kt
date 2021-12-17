package com.example.covideu.view.ViewModels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.covideu.model.covidNews.allCovidNews.NewModel
import com.example.covideu.model.covidNews.allHealthNews.AllHeathNewsModel
import com.example.covideu.model.covidNews.allVaccineNews.allVaccineNews
import com.example.covideu.model.covidNews.allVaccineNews.getAllVaccineNewsModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

private const val TAG = "CovidNewsViewModel"
class CovidNewsViewModel:ViewModel() {

    private val apiRepo = ApiRepositoryCovidData.get()
    val covidAllNewsLiveData = MutableLiveData<List<NewModel>>()
    val covidAllHeathLiveData = MutableLiveData<List<AllHeathNewsModel>>()
    val covid19VaccineLiveData = MutableLiveData<List<allVaccineNews>>()

    val CovidLiveDataError = MutableLiveData<String?>()


    fun callAllCovidNews(){

        viewModelScope.launch(Dispatchers.IO){
            val response = apiRepo.getAllCovid19News()
            try {
                if(response.isSuccessful){

                    response.body()?.run{
                        Log.d(TAG,this.toString())

                        covidAllNewsLiveData.postValue(this)

                    }


                }else{
                    CovidLiveDataError.postValue(response.message())


                }

            }catch (e: Exception){
                CovidLiveDataError.postValue(e.toString())



            }
        }
    }

    fun callAllHealthNews(){

        viewModelScope.launch(Dispatchers.IO){
            val response = apiRepo.getAllHealthNews()
            try {
                if(response.isSuccessful){

                    response.body()?.run{
                        Log.d(TAG,this.toString())

                        covidAllHeathLiveData.postValue(this)

                    }


                }else{
                    CovidLiveDataError.postValue(response.message())


                }

            }catch (e: Exception){
                CovidLiveDataError.postValue(e.toString())



            }
        }
    }

    fun callAllVaccineNews(){

        viewModelScope.launch(Dispatchers.IO){
            val response = apiRepo.getAllVaccineNews()
            try {
                if(response.isSuccessful){

                    response.body()?.run{
                        Log.d(TAG,this.toString())

                        covid19VaccineLiveData.postValue(this)

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