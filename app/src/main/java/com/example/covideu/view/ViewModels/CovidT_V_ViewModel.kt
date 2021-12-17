package com.example.covideu.view.ViewModels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.covideu.model.VaccineAndTreatments.Treatment.getAllTreatmentsData
import com.example.covideu.model.VaccineAndTreatments.Treatment.getClinicalTreatments
import com.example.covideu.model.VaccineAndTreatments.Treatment.getFDA_Approvedtreatments
import com.example.covideu.model.VaccineAndTreatments.Vaccines.*
import com.example.covideu.model.get.all.covid.Countries.Statistical.getAllCovid19CountriesStatisticalDataItemModel
import com.example.covideu.model.getAllAfricanCountries.getAllAfricanCountriesModel
import com.example.covideu.model.getAllAsianCountries.getAll_AsianCountriesModel
import com.example.covideu.model.getAllAustralianAndOceaniancounties.getAllAustralianAndOceanianCountriesModel
import com.example.covideu.model.getAllEuropeanCountries.getAllEuropeanCountriesModel
import com.example.covideu.model.getAllNorthernAmericanCountries.getAllNorthernAmericanCountriesModel
import com.example.covideu.model.getAllSouthernAmericanCountries.getAllSouthernAmericanCountriesModel
import com.example.covideu.model.getCountriesCovid.getOnlyCountriesCovid19DataModel
import com.example.covideu.model.worldCovidCases.worldCovidCaesModelItem
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

    fun callPhaseOneLiveData(){

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
    fun callPhaseTwoLiveData(){

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

    fun callPhaseThreeLiveData(){

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

    fun callPhaseFourLiveData(){

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