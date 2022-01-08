package com.example.covideu.view.ViewModels.bookOfCovid

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.covideu.repostries.bookOfCovidFireBaseRepository
import java.lang.Exception

class deleteBookOfCovidViewModel:ViewModel() {

    private val bookOfCoivdRepository = bookOfCovidFireBaseRepository()
    val userLiveDataError = MutableLiveData<String>()
    val userLiveDataSuccessful = MutableLiveData<String>()

    fun deleteAnImage(ImageUri:String){
        try {
            bookOfCoivdRepository.deleteImage(ImageUri).delete()
            userLiveDataSuccessful.postValue("Successfully deleted")

        }catch (e: Exception){
            userLiveDataError.postValue("Failed to delete file")

        }

    }
}