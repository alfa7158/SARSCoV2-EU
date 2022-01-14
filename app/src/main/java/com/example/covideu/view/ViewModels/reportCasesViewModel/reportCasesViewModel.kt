package com.example.covideu.view.ViewModels.reportCasesViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.covideu.database.reoprtCases.ReportCasesDataClass
import com.example.covideu.databinding.FragmentFDAApprovedTreatmentBinding
import com.example.covideu.databinding.FragmentReportCovidCasesBinding
import com.example.covideu.repostries.reportCovidCasesRepo
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.toObject

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


    fun addCommentsFireStore(uid:String,id: Int,lan:Double,lat:Double,numberOfInfected:Int, relationship:String) {

        reportCovidRepository.addReportCases().add(
            ReportCasesDataClass(FirebaseAuth.getInstance().uid.toString(),id,lan,lat,numberOfInfected,relationship)
        )

            .addOnSuccessListener {

                reportLiveDataSuccessful.postValue("Successful reported")

            }.addOnFailureListener {
                reportCasesLiveDataError.postValue("Failed to report")


            }
    }






}