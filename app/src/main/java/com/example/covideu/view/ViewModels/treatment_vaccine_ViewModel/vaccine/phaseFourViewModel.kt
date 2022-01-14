package com.example.covideu.view.ViewModels.treatment_vaccine_ViewModel.vaccine

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.covideu.model.VaccineAndTreatments.Vaccines.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

private const val TAG = "phaseFourViewModel"
class phaseFourViewModel:ViewModel() {

    private val apiRepo = ApiRepositoryCovidData.get()
    val CovidLiveDataError = MutableLiveData<String?>()


    val covid19PhaseFourLiveData = MutableLiveData<List<getPhase_four_vaccines>>()

    val covid19PhaseFourLiveDataDetails = MutableLiveData<getPhase_four_vaccines>()
    val covid19PhaseFourLiveDataSuccessful = MutableLiveData<String>()
    val covid19PhaseFourLiveDataError = MutableLiveData<String>()



    fun callPhaseFour(){

        viewModelScope.launch(Dispatchers.IO){
            val response = apiRepo.getPhaseFour()
            try {
                if(response.isSuccessful){

                    response.body()?.run{
                        Log.d(TAG,this.toString())

                        covid19PhaseFourLiveData.postValue(this)
                        covid19PhaseFourLiveDataSuccessful.postValue("successful")
                    }


                }else{
                    CovidLiveDataError.postValue(response.message())
                    covid19PhaseFourLiveDataError.postValue(response.message())
                }

            }catch (e: Exception){
                CovidLiveDataError.postValue(e.toString())



            }
        }
    }
}