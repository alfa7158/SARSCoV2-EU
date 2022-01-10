package com.example.covideu.view.ViewModels.t_v_ViewModel.vaccine

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.covideu.model.VaccineAndTreatments.Treatment.getAllTreatmentsData
import com.example.covideu.model.VaccineAndTreatments.Treatment.getClinicalTreatments
import com.example.covideu.model.VaccineAndTreatments.Treatment.getFDA_Approvedtreatments
import com.example.covideu.model.VaccineAndTreatments.Vaccines.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

private const val TAG = "phaseTwoViewModel"
class phaseTwoViewModel:ViewModel() {
    private val apiRepo = ApiRepositoryCovidData.get()

    val covid19PhaseTwoLiveData  = MutableLiveData<List<getPhase_two_vaccines>>()
    val covid19PhaseTwoLiveDataDetails = MutableLiveData<getPhase_two_vaccines>()
    val covid19PhaseTwoLiveDataSuccessful = MutableLiveData<String>()
    val covid19PhaseTwoLiveDataError = MutableLiveData<String>()



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