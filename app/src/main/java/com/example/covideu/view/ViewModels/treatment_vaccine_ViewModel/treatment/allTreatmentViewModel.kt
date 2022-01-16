package com.example.covideu.view.ViewModels.treatment_vaccine_ViewModel.treatment

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.covideu.model.VaccineAndTreatments.Treatment.getAllTreatmentsData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
/**
 * This all  treatment data view model which is responsible for calling  all  treatment data from
 * the database
 */
private const val TAG = "allTreatmentViewModel"
class allTreatmentViewModel:ViewModel() {
    private val apiRepo = ApiRepositoryCovidData.get()
    val CovidLiveDataError = MutableLiveData<String?>()
    val covidAllTreatmentsLiveData = MutableLiveData<List<getAllTreatmentsData>>()
    val covidAllTreatmentsLiveDataDetails = MutableLiveData<getAllTreatmentsData>()
    val treatmentLiveDataSuccessful = MutableLiveData<String>()


    /**
     * The function below is responsible for getting all  treatment  data from the api
     */


    fun callAllCovidTreatment(){

        viewModelScope.launch(Dispatchers.IO){
            val response = apiRepo.getAllTreatmentData()
            try {
                if(response.isSuccessful){

                    response.body()?.run{
                        Log.d(TAG,this.toString())

                        covidAllTreatmentsLiveData.postValue(this)
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