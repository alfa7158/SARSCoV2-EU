package com.example.covideu.view.ViewModels.bookOfCovidViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.covideu.database.bookOfCovid.BookOfCovidDataClassComments
import com.example.covideu.database.bookOfCovid.BookOfCovidDataClassFavorite
import com.example.covideu.repostries.bookOfCovidFireBaseRepository
import com.google.firebase.auth.FirebaseAuth

class bookOfCovidCommentsViewModel:ViewModel() {
    private val bookOfCoivdRepository = bookOfCovidFireBaseRepository()

    var successfulLiveData = MutableLiveData<String>()
    var errorLiveData = MutableLiveData<String>()

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