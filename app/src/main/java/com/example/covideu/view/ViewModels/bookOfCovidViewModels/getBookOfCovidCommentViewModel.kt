package com.example.covideu.view.ViewModels.bookOfCovidViewModels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.covideu.database.bookOfCovid.BookOfCovidDataClassComments
import com.example.covideu.repostries.bookOfCovidFireBaseRepository
import com.google.firebase.firestore.ktx.toObject

private const val TAG = "getBookOfCovidCommentVi"
class getBookOfCovidCommentViewModel:ViewModel() {
    private val bookOfCoivdRepository = bookOfCovidFireBaseRepository()
    val commentLiveDataError = MutableLiveData<String>()
    val commentLiveDataSuccessful = MutableLiveData<String>()
    val commentLiveData = MutableLiveData<List<BookOfCovidDataClassComments>>()

    fun getBookOfCovidComments(){
        var commentList = mutableListOf<BookOfCovidDataClassComments>()
        var imageSnapShot = bookOfCoivdRepository.getCommentsBookOfCovid().get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    var x = document.toObject<BookOfCovidDataClassComments>()

                    commentList.add(x)

                    Log.d(TAG, "${document.id} => ${document.data}")


                }

                commentLiveData.postValue(commentList)


            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents.", exception)
            }
    }

}