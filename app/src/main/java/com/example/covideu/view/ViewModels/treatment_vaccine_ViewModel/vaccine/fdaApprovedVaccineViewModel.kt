package com.example.covideu.view.ViewModels.treatment_vaccine_ViewModel.vaccine

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.covideu.model.VaccineAndTreatments.Vaccines.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

private const val TAG = "fdaApprovedVaccineViewM"
class fdaApprovedVaccineViewModel:ViewModel() {

    private val apiRepo = ApiRepositoryCovidData.get()
    val CovidLiveDataError = MutableLiveData<String?>()


    val covid19FDAApprovedVaccineLiveData = MutableLiveData<List<getFDA_ApprovedVaccines>>()
    val covid19FDAApprovedVaccineLiveDataDetails = MutableLiveData<getFDA_ApprovedVaccines>()
    val covid19FDAApprovedVaccineLiveDataSuccessful = MutableLiveData<String>()
    val covid19FDAApprovedVaccineLiveDataError = MutableLiveData<String>()

    /**
     * The function below is responsible for getting all  approved vaccine  data from the api
     */

    fun callFdaAllVaccinesTreatment(){

        viewModelScope.launch(Dispatchers.IO){
            val response = apiRepo.getAll_FDA_VaccinesApproved()
            try {
                if(response.isSuccessful){

                    response.body()?.run{
                        Log.d(TAG,this.toString())

                        covid19FDAApprovedVaccineLiveData.postValue(this)
                        covid19FDAApprovedVaccineLiveDataSuccessful.postValue("successful")
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