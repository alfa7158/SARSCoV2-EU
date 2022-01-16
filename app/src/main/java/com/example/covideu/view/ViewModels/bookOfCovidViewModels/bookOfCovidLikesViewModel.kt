package com.example.covideu.view.ViewModels.bookOfCovidViewModels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.covideu.database.bookOfCovid.BookOfCovidDataClassFavorite
import com.example.covideu.repostries.bookOfCovidFireBaseRepository

/**
 * This is the comments class and it hold the add likes function
 */
private const val TAG = "bookOfCovidLikesViewMod"
class bookOfCovidLikesViewMode:ViewModel() {

    private val bookOfCoivdRepository = bookOfCovidFireBaseRepository()

    var successfulLiveData = MutableLiveData<String>()
    var errorLiveData = MutableLiveData<String>()


    /**
     * The function below job is to add likes to the firebase using the image id
     */
    fun addFavoriteFireStore(imageId: String) {

        bookOfCoivdRepository.addFavorite().add(
            BookOfCovidDataClassFavorite(imageId))


            .addOnSuccessListener {

                successfulLiveData.postValue("Successful added to favorite")

            }.addOnFailureListener {
                errorLiveData.postValue("Failed add to favorite")


            }
    }


}