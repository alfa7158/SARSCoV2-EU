package com.example.covideu.view.ViewModels.bookOfCovid

import androidx.lifecycle.MutableLiveData
import com.example.covideu.database.bookOfCovid.*
import com.example.covideu.repostries.bookOfCovidFireBaseRepository

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.ktx.toObject

private const val TAG = "getBookOfCovidDocxViewM"
class getBookOfCovidDocxViewModel:ViewModel() {
    private val bookOfCoivdRepository = bookOfCovidFireBaseRepository()
    val userLiveDataError = MutableLiveData<String>()
    val userLiveDataSuccessful = MutableLiveData<String>()
    val uriLiveDataForDocx = MutableLiveData<List<bookOfCovidDataClassDocx>>()

    fun getBookOfCovidDocx(){
        var DocxList = mutableListOf<bookOfCovidDataClassDocx>()
        var imageSnapShot = bookOfCoivdRepository.getDocxBookOfCovid().get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    var x = document.toObject<bookOfCovidDataClassDocx>()

                    DocxList.add(x)

                    Log.d(TAG, "${document.id} => ${document.data}")


                }

                uriLiveDataForDocx.postValue(DocxList)


            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents.", exception)
            }
    }
}