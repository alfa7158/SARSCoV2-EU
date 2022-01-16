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
 * This all vaccine data view model which is responsible for calling all vaccine data from
 * the database
 */
private const val TAG = "AllVaccineViewModel"
class AllVaccineViewModel:ViewModel() {

    private val apiRepo = ApiRepositoryCovidData.get()
    val CovidLiveDataError = MutableLiveData<String?>()

    val covidAllVaccinesLiveData = MutableLiveData<List<getAllVaccinesDataItem>>()
    val covidAllVaccinesLiveDataDetails = MutableLiveData<getAllVaccinesDataItem>()
    val covidAllVaccinesLiveSuccessful = MutableLiveData<String>()
    val covidAllVaccinesLiveError = MutableLiveData<String>()
    /**
     * The function below is responsible for getting all  vaccine  data from the api
     */

    fun callAllVaccinesTreatment(){

        viewModelScope.launch(Dispatchers.IO){
            val response = apiRepo.getAllVaccinesData()
            try {
                if(response.isSuccessful){

                    response.body()?.run{
                        Log.d(TAG,this.toString())

                        covidAllVaccinesLiveData.postValue(this)

                    }


                }else{
                    CovidLiveDataError.postValue(response.message())
                    covidAllVaccinesLiveError.postValue(response.message())

                }

            }catch (e: Exception){
                covidAllVaccinesLiveError.postValue(e.toString())



            }
        }
    }
}