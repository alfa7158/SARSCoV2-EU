package com.example.covideu.view.ViewModels.bookOfCovidViewModels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.covideu.database.bookOfCovid.BookOfCovidDataClassComments
import com.example.covideu.database.bookOfCovid.BookOfCovidDataClassFavorite
import com.example.covideu.repostries.bookOfCovidFireBaseRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.toObject

/**
 * This is the comments class and it hold the add likes function
 */
private const val TAG = "bookOfCovidLikesViewMod"
class bookOfCovidLikesViewMode:ViewModel() {

    private val bookOfCoivdRepository = bookOfCovidFireBaseRepository()
    var LikesLiveData = MutableLiveData<BookOfCovidDataClassFavorite>()
    var successfulLiveData = MutableLiveData<String>()
    var errorLiveData = MutableLiveData<String>()


    /**
     * The function below job is to add likes to the firebase using the image id
     */
    fun addFavoriteFireStore(imageId: String, user:String) {

        bookOfCoivdRepository.addFavorite(imageId).set(
            BookOfCovidDataClassFavorite(imageId,user)).addOnSuccessListener {

                successfulLiveData.postValue("Successful added to favorite")

            }.addOnFailureListener {
                errorLiveData.postValue("Failed add to favorite")


            }
    }

//    fun getBookOfCovidLikes(likeId:String,imageId:String){
//        var favoriteList = mutableListOf<BookOfCovidDataClassFavorite>()
//        var imageSnapShot = bookOfCoivdRepository.getCommentsBookOfCovid().get()
//            .addOnSuccessListener { result ->
//                bookOfCoivdRepository.deleteFavoriteFireStore(likeId,imageId)
//                    .addOnSuccessListener { result ->
//                        for (document in result) {
//                            bookOfCoivdRepository.likesCollection.document(document.id).delete()
//                        }
//
//                    }
//                    .addOnFailureListener { exception ->
//                        Log.w(TAG, "Error getting documents.", exception)
//                    }
//
//                LikesLiveData.postValue(favoriteList)
//
//
//            }
//            .addOnFailureListener { exception ->
//                Log.w(TAG, "Error getting documents.", exception)
//            }
//    }


}