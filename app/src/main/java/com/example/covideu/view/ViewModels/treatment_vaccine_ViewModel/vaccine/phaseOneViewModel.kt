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
 * This all phase 1 data view model which is responsible for calling all phase 1 data from
 * the database
 */
private const val TAG = "phaseOneViewModel"
class phaseOneViewModel:ViewModel() {
    private val apiRepo = ApiRepositoryCovidData.get()
    val CovidLiveDataError = MutableLiveData<String?>()


    val covid19PhaseOneLiveData = MutableLiveData<List<getPhase_one_vaccines>>()
    val covid19PhaseOneLiveDataDetails = MutableLiveData<getPhase_one_vaccines>()
    val covid19PhaseOneLiveDataSuccessful = MutableLiveData<String>()
    val covid19PhaseOneLiveDataError = MutableLiveData<String>()




    /**
     * The function below is responsible for getting all  phase 1  data from the api
     */

    fun callPhaseOne(){

        viewModelScope.launch(Dispatchers.IO){
            val response = apiRepo.getPhaseOne()
            try {
                if(response.isSuccessful){

                    response.body()?.run{
                        Log.d(TAG,this.toString())

                        covid19PhaseOneLiveData.postValue(this)
                        covid19PhaseOneLiveDataSuccessful.postValue("successful")
                    }


                }else{
                    covid19PhaseOneLiveDataError.postValue(response.message())


                }

            }catch (e: Exception){
                covid19PhaseOneLiveDataError.postValue(e.toString())



            }
        }
    }

}