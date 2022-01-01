package com.example.covideu.view.ViewModels.t_v_ViewModel.treatment

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

private const val TAG = "allClinicalViewModel"
class allClinicalViewModel:ViewModel() {

    private val apiRepo = ApiRepositoryCovidData.get()
    val CovidLiveDataError = MutableLiveData<String?>()


    val covid19ClinicalLiveData = MutableLiveData<List<getClinicalTreatments>>()
    val covid19ClinicalLiveDataDetails = MutableLiveData<getClinicalTreatments>()



    fun callClinicalLiveData(){

        viewModelScope.launch(Dispatchers.IO){
            val response = apiRepo.getAllClinicalTreatment()
            try {
                if(response.isSuccessful){

                    response.body()?.run{
                        Log.d(TAG,this.toString())

                        covid19ClinicalLiveData.postValue(this)

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