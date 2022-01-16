package com.example.covideu.view.ViewModels.bookOfCovidViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.covideu.database.bookOfCovid.BookOfCovidDataClassComments
import com.example.covideu.database.bookOfCovid.BookOfCovidDataClassFavorite
import com.example.covideu.repostries.bookOfCovidFireBaseRepository
import com.google.firebase.auth.FirebaseAuth

/**
 * This is the comments class and it hold the add comment function
 */
class bookOfCovidCommentsViewModel:ViewModel() {
    private val bookOfCoivdRepository = bookOfCovidFireBaseRepository()

    var successfulLiveData = MutableLiveData<String>()
    var errorLiveData = MutableLiveData<String>()

    /**
     * The function below job is to add comments to the firebase using the image id
     */
    fun addCommentsFireStore(imageId: String) {

        bookOfCoivdRepository.addComments().add(
            BookOfCovidDataClassComments(imageId, FirebaseAuth.getInstance().uid.toString())
        )

            .addOnSuccessListener {

                successfulLiveData.postValue("Successful added to favorite")

            }.addOnFailureListener {
                errorLiveData.postValue("Failed add to favorite")


            }
    }
}