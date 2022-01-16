package com.example.covideu.view.ViewModels.treatment_vaccine_ViewModel.treatment

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.covideu.model.VaccineAndTreatments.Treatment.getClinicalTreatments
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
/**
 * This all clinical data view model which is responsible for calling all clinical data from
 * the database
 */
private const val TAG = "allClinicalViewModel"
class allClinicalViewModel:ViewModel() {

    private val apiRepo = ApiRepositoryCovidData.get()
    val CovidLiveDataError = MutableLiveData<String?>()


    val covid19ClinicalLiveData = MutableLiveData<List<getClinicalTreatments>>()
    val covid19ClinicalLiveDataDetails = MutableLiveData<getClinicalTreatments>()
    val treatmentLiveDataSuccessful = MutableLiveData<String>()

    /**
     * The function below is responsible for getting all  clinical  data from the api
     */

    fun callClinicalLiveData(){

        viewModelScope.launch(Dispatchers.IO){
            val response = apiRepo.getAllClinicalTreatment()
            try {
                if(response.isSuccessful){

                    response.body()?.run{
                        Log.d(TAG,this.toString())

                        covid19ClinicalLiveData.postValue(this)
                        treatmentLiveDataSuccessful.postValue("successful")
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