package com.example.covideu.view.ViewModels.treatment_vaccine_ViewModel.vaccine

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.covideu.model.VaccineAndTreatments.Vaccines.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
/**
 * This all phase 2 data view model which is responsible for calling all phase 2 data from
 * the database
 */
private const val TAG = "phaseTwoViewModel"
class phaseTwoViewModel:ViewModel() {
    private val apiRepo = ApiRepositoryCovidData.get()

    val covid19PhaseTwoLiveData  = MutableLiveData<List<getPhase_two_vaccines>>()
    val covid19PhaseTwoLiveDataDetails = MutableLiveData<getPhase_two_vaccines>()
    val covid19PhaseTwoLiveDataSuccessful = MutableLiveData<String>()
    val covid19PhaseTwoLiveDataError = MutableLiveData<String>()

    /**
     * The function below is responsible for getting all  phase 4  data from the api
     */

    fun callPhaseTwo(){

        viewModelScope.launch(Dispatchers.IO){
            val response = apiRepo.getPhaseTwo()
            try {
                if(response.isSuccessful){

                    response.body()?.run{
                        Log.d(TAG,this.toString())

                        covid19PhaseTwoLiveData.postValue(this)
                        covid19PhaseTwoLiveDataSuccessful.postValue("successful")
                    }


                }else{
                    covid19PhaseTwoLiveDataError.postValue(response.message())

                }

            }catch (e: Exception){
                covid19PhaseTwoLiveDataError.postValue(e.toString())



            }
        }
    }
}