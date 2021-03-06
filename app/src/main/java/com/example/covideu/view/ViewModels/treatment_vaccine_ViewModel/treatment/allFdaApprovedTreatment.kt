package com.example.covideu.view.ViewModels.treatment_vaccine_ViewModel.treatment

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.covideu.model.VaccineAndTreatments.Treatment.getFDA_Approvedtreatments
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
/**
 * This all fda approved treatment data view model which is responsible for calling  all fda approved treatment data from
 * the database
 */
private const val TAG = "allFdaApprovedTreatment"
class allFdaApprovedTreatment:ViewModel() {
    private val apiRepo = ApiRepositoryCovidData.get()
    val CovidLiveDataError = MutableLiveData<String?>()


    val covid19ApprovedTreatmentsLiveData = MutableLiveData<List<getFDA_Approvedtreatments>>()
    val covid19ApprovedTreatmentsLiveDataDetails= MutableLiveData<getFDA_Approvedtreatments>()
    val treatmentLiveDataSuccessful = MutableLiveData<String>()
    /**
     * The function below is responsible for getting all  fda approved  data from the api
     */

    fun callApprovedTreatmentsLiveData(){

        viewModelScope.launch(Dispatchers.IO){
            val response = apiRepo.getAllApprovedFDATreatmentData()
            try {
                if(response.isSuccessful){

                    response.body()?.run{
                        Log.d(TAG,this.toString())

                        covid19ApprovedTreatmentsLiveData.postValue(this)
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