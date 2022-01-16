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
 * This all phase 3 data view model which is responsible for calling all phase 3 data from
 * the database
 */
private const val TAG = "phaseThreeViewModel"
class phaseThreeViewModel:ViewModel() {

    private val apiRepo = ApiRepositoryCovidData.get()

    val covid19PhaseThreeLiveData  = MutableLiveData<List<getPhase_three_vaccines>>()
    val covid19PhaseThreeLiveDataDetails = MutableLiveData<getPhase_three_vaccines>()
    val covid19PhaseThreeLiveDataSuccessful = MutableLiveData<String>()
    val covid19PhaseThreeLiveDataError = MutableLiveData<String>()


    /**
     * The function below is responsible for getting all  phase 3  data from the api
     */
    fun callPhaseThree(){

        viewModelScope.launch(Dispatchers.IO){
            val response = apiRepo.getPhaseThree()
            try {
                if(response.isSuccessful){

                    response.body()?.run{
                        Log.d(TAG,this.toString())

                        covid19PhaseThreeLiveData.postValue(this)
                        covid19PhaseThreeLiveDataSuccessful.postValue("Successful")
                    }


                }else{
                    covid19PhaseThreeLiveDataError.postValue(response.message())

                }

            }catch (e: Exception){
                covid19PhaseThreeLiveDataError.postValue(e.toString())



            }
        }
    }

}