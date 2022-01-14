package com.example.covideu.view.ViewModels.bookOfCovidViewModels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.covideu.database.bookOfCovid.BookOfCovidDataClassFavorite
import com.example.covideu.repostries.bookOfCovidFireBaseRepository

private const val TAG = "bookOfCovidLikesViewMod"
class bookOfCovidLikesViewMode:ViewModel() {

    private val bookOfCoivdRepository = bookOfCovidFireBaseRepository()

    var successfulLiveData = MutableLiveData<String>()
    var errorLiveData = MutableLiveData<String>()



    fun addFavoriteFireStore(id: String) {

        bookOfCoivdRepository.addFavorite().add(
            BookOfCovidDataClassFavorite(id))


            .addOnSuccessListener {

                successfulLiveData.postValue("Successful added to favorite")

            }.addOnFailureListener {
                errorLiveData.postValue("Failed add to favorite")


            }
    }


}