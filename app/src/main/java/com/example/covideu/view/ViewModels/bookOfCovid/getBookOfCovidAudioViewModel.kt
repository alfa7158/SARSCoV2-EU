package com.example.covideu.view.ViewModels.bookOfCovid

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.covideu.database.bookOfCovid.*
import com.example.covideu.repostries.bookOfCovidFireBaseRepository
import com.google.firebase.firestore.ktx.toObject

private const val TAG = "getBookOfCovidAudioView"
class getBookOfCovidAudioViewModel:ViewModel() {
    private val bookOfCoivdRepository = bookOfCovidFireBaseRepository()

    val userLiveDataError = MutableLiveData<String>()
    val userLiveDataSuccessful = MutableLiveData<String>()
    val uriLiveDataForAudio = MutableLiveData<List<bookOfCovidDataClassAudio>>()
    val uriLiveDataForAudioDetails = MutableLiveData<bookOfCovidDataClassAudio>()

//    fun getBookOfCovidAudio(uid: String){
//
//
//
//
////        val storageRef = storage.reference.child("bookOfCoivdImages")
////        val imageList:MutableLiveData<bookOfCovidDataClass> = MutableLiveData<bookOfCovidDataClass>()
//
////        val listAllTask: Task<ListResult> = bookOfCoivdRepository.getAudioBookOfCovid(uid).listAll().addOnCompleteListener { result ->
////            val item:List<StorageReference> = result.result!!.items
////
////            item.forEachIndexed { index, storageReference ->
////                storageReference.downloadUrl.addOnSuccessListener {
////                    Log.d("bookOfCovidDataClass","$it")
////                    uriLiveDataForAudio.postValue(bookOfCovidDataClassAudio(it.toString()))
////                }.addOnCompleteListener{
////                    userLiveDataSuccessful.postValue("Successfully loaded")
////
////                }.addOnFailureListener{
////                    userLiveDataError.postValue("failed to load")
////
////
////                }
////
////            }
////        }
//
//    }

    fun getBookOfCovidAudio(){
        var audioList = mutableListOf<bookOfCovidDataClassAudio>()
        var imageSnapShot = bookOfCoivdRepository.getAudioBookOfCovid().get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    var x = document.toObject<bookOfCovidDataClassAudio>()

                    audioList.add(x)

                    Log.d(TAG, "${document.id} => ${document.data}")


                }

                uriLiveDataForAudio.postValue(audioList)


            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents.", exception)
            }
    }
}