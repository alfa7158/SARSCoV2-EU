package com.example.covideu.view.ViewModels.t_v_ViewModel.vaccine

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.covideu.model.VaccineAndTreatments.Treatment.getAllTreatmentsData
import com.example.covideu.model.VaccineAndTreatments.Treatment.getClinicalTreatments
import com.example.covideu.model.VaccineAndTreatments.Treatment.getFDA_Approvedtreatments
import com.example.covideu.model.VaccineAndTreatments.Vaccines.*
import com.example.covideu.model.getAllAsianCountries.getAll_AsianCountriesModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

private const val TAG = "AllVaccineViewModel"
class AllVaccineViewModel:ViewModel() {

    private val apiRepo = ApiRepositoryCovidData.get()
    val CovidLiveDataError = MutableLiveData<String?>()

    val covidAllVaccinesLiveData = MutableLiveData<List<getAllVaccinesDataItem>>()
    val covidAllVaccinesLiveDataDetails = MutableLiveData<getAllVaccinesDataItem>()
    val covidAllVaccinesLiveSuccessful = MutableLiveData<String>()
    val covidAllVaccinesLiveError = MutableLiveData<String>()


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