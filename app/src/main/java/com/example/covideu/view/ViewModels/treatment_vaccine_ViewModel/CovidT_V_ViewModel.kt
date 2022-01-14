package com.example.covideu.view.ViewModels.treatment_vaccine_ViewModel

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

private const val TAG = "CovidT_V_ViewModel"
class CovidT_V_ViewModel:ViewModel() {
    private val apiRepo = ApiRepositoryCovidData.get()
    val CovidLiveDataError = MutableLiveData<String?>()

    val covidAllTreatmentsLiveData = MutableLiveData<List<getAllTreatmentsData>>()
    val covidAllVaccinesLiveData = MutableLiveData<List<getAllVaccinesDataItem>>()
    val covid19ApprovedTreatmentsLiveData = MutableLiveData<List<getFDA_Approvedtreatments>>()
    val covid19ClinicalLiveData = MutableLiveData<List<getClinicalTreatments>>()
    val covid19PhaseOneLiveData = MutableLiveData<List<getPhase_one_vaccines>>()
    val covid19PhaseTwoLiveData  = MutableLiveData<List<getPhase_two_vaccines>>()
    val covid19PhaseThreeLiveData  = MutableLiveData<List<getPhase_three_vaccines>>()
    val covid19PhaseFourLiveData = MutableLiveData<List<getPhase_four_vaccines>>()


    fun callAllCovidTreatment(){

        viewModelScope.launch(Dispatchers.IO){
            val response = apiRepo.getAllTreatmentData()
            try {
                if(response.isSuccessful){

                    response.body()?.run{
                        Log.d(TAG,this.toString())

                        covidAllTreatmentsLiveData.postValue(this)

                    }


                }else{
                    CovidLiveDataError.postValue(response.message())


                }

            }catch (e: Exception){
                CovidLiveDataError.postValue(e.toString())



            }
        }
    }


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


                }

            }catch (e: Exception){
                CovidLiveDataError.postValue(e.toString())



            }
        }
    }

    fun callApprovedTreatmentsLiveData(){

        viewModelScope.launch(Dispatchers.IO){
            val response = apiRepo.getAllApprovedFDATreatmentData()
            try {
                if(response.isSuccessful){

                    response.body()?.run{
                        Log.d(TAG,this.toString())

                        covid19ApprovedTreatmentsLiveData.postValue(this)

                    }


                }else{

                    CovidLiveDataError.postValue(response.message())

                }

            }catch (e: Exception){
                CovidLiveDataError.postValue(e.toString())



            }
        }
    }

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

    fun callPhaseOne(){

        viewModelScope.launch(Dispatchers.IO){
            val response = apiRepo.getPhaseOne()
            try {
                if(response.isSuccessful){

                    response.body()?.run{
                        Log.d(TAG,this.toString())

                        covid19PhaseOneLiveData.postValue(this)

                    }


                }else{
                    CovidLiveDataError.postValue(response.message())


                }

            }catch (e: Exception){
                CovidLiveDataError.postValue(e.toString())



            }
        }
    }
    fun callPhaseTwo(){

        viewModelScope.launch(Dispatchers.IO){
            val response = apiRepo.getPhaseTwo()
            try {
                if(response.isSuccessful){

                    response.body()?.run{
                        Log.d(TAG,this.toString())

                        covid19PhaseTwoLiveData.postValue(this)

                    }


                }else{
                    CovidLiveDataError.postValue(response.message())

                }

            }catch (e: Exception){
                CovidLiveDataError.postValue(e.toString())



            }
        }
    }

    fun callPhaseThree(){

        viewModelScope.launch(Dispatchers.IO){
            val response = apiRepo.getPhaseThree()
            try {
                if(response.isSuccessful){

                    response.body()?.run{
                        Log.d(TAG,this.toString())

                        covid19PhaseThreeLiveData.postValue(this)

                    }


                }else{
                    CovidLiveDataError.postValue(response.message())

                }

            }catch (e: Exception){
                CovidLiveDataError.postValue(e.toString())



            }
        }
    }

    fun callPhaseFour(){

        viewModelScope.launch(Dispatchers.IO){
            val response = apiRepo.getPhaseFour()
            try {
                if(response.isSuccessful){

                    response.body()?.run{
                        Log.d(TAG,this.toString())

                        covid19PhaseFourLiveData.postValue(this)

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