package com.example.covideu.view.ViewModels.reportCasesViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.covideu.database.reoprtCases.ReportCasesDataClass
import com.example.covideu.repostries.reportCovidCasesRepo
import com.google.firebase.auth.FirebaseAuth

/**
 * This the report cases view model class  which contains one functions that is responsible for
 * adding the report to the Firestore
 */
private const val TAG = "reportCasesViewModel"
class reportCasesViewModel:ViewModel() {

    private val reportCovidRepository = reportCovidCasesRepo()
    val reportCasesLiveDataError = MutableLiveData<String>()
    val reportLiveDataSuccessful = MutableLiveData<String>()
    val reportCasesLiveData = MutableLiveData<List<ReportCasesDataClass>>()


//    fun getReportedCovidCases(){
//        var reportedList = mutableListOf<ReportCasesDataClass>()
//        var imageSnapShot = bookOfCoivdRepository.getCommentsBookOfCovid().get()
//            .addOnSuccessListener { result ->
//                for (document in result) {
//                    var x = document.toObject<ReportCasesDataClass>()
//
//                    reportedList.add(x)
//
//                    Log.d(TAG, "${document.id} => ${document.data}")
//                    reportLiveDataSuccessful.postValue("Successful")
//
//                }
//
//                reportCasesLiveData.postValue(reportedList)
//
//
//            }
//            .addOnFailureListener { exception ->
//                reportCasesLiveDataError.postValue(exception.toString())
//                Log.w(TAG, "Error getting documents.", exception)
//            }
//    }


    fun addReportFireStore(uid:String, idType:String, id: String, lan:Double, lat:Double, numberOfInfected:Int, relationship:String,address:String) {

        reportCovidRepository.addReportCases().add(
            ReportCasesDataClass(FirebaseAuth.getInstance().uid.toString(),idType,id,lan,lat,numberOfInfected,relationship,address)
        )

            .addOnSuccessListener {

                reportLiveDataSuccessful.postValue("Successful reported")

            }.addOnFailureListener {
                reportCasesLiveDataError.postValue("Failed to report")


            }
    }






}