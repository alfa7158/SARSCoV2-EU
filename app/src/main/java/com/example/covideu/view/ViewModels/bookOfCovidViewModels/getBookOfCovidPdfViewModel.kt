package com.example.covideu.view.ViewModels.bookOfCovidViewModels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.covideu.database.bookOfCovid.*
import com.example.covideu.repostries.bookOfCovidFireBaseRepository
import com.google.firebase.firestore.ktx.toObject

/**
 * This class hold a function for getting the pdf
 */
private const val TAG = "getBookOfCovidPdfViewMo"
class getBookOfCovidPdfViewModel:ViewModel() {

    private val bookOfCoivdRepository = bookOfCovidFireBaseRepository()
    val userLiveDataError = MutableLiveData<String>()
    val userLiveDataSuccessful = MutableLiveData<String>()
    val uriLiveDataForPdf = MutableLiveData<List<bookOfCovidDataClassPdf>>()


    /**
     * The function below job is to get the pdf from the firebase
     */
    fun getBookOfCovidPdf(){
        var pdfList = mutableListOf<bookOfCovidDataClassPdf>()
        var imageSnapShot = bookOfCoivdRepository.getPdfBookOfCovid().get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    var x = document.toObject<bookOfCovidDataClassPdf>()

                    pdfList.add(x)

                    Log.d(TAG, "${document.id} => ${document.data}")


                }

                uriLiveDataForPdf.postValue(pdfList)


            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents.", exception)
            }
    }

}