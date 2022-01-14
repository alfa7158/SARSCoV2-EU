package com.example.covideu.view.ViewModels.bookOfCovidViewModels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.covideu.database.bookOfCovid.*
import com.example.covideu.repostries.bookOfCovidFireBaseRepository
import com.google.firebase.firestore.ktx.toObject

private const val TAG = "getBookOfCovidVideosVie"
class getBookOfCovidVideosViewModel:ViewModel(){

    private val bookOfCoivdRepository = bookOfCovidFireBaseRepository()
    val userLiveDataError = MutableLiveData<String>()
    val userLiveDataSuccessful = MutableLiveData<String>()
    val uriLiveDataForVideos = MutableLiveData<List<bookOfCovidDataClassVideos>>()
    val uriLiveDataForVideosDetails = MutableLiveData<bookOfCovidDataClassVideos>()


    fun getBookOfCovidVideos(){
        var videoList = mutableListOf<bookOfCovidDataClassVideos>()
        var videoSnapShot = bookOfCoivdRepository.getVideosBookOfCovid().get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    var x = document.toObject<bookOfCovidDataClassVideos>()

                    videoList.add(x)
                    Log.d("VideoViewModel",videoList.toString())

                    Log.d(TAG, "${document.id} => ${document.data}")


                }

                uriLiveDataForVideos.postValue(videoList)


            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents.", exception)
            }
    }








//    fun getBookOfCovidVideos(uid: String){
//
////        val storageRef = storage.reference.child("bookOfCoivdImages")
////        val imageList:MutableLiveData<bookOfCovidDataClass> = MutableLiveData<bookOfCovidDataClass>()
//
//        val listAllTask: Task<ListResult> = bookOfCoivdRepository.getVideosBookOfCovid(uid).listAll().addOnCompleteListener { result ->
//            val item:List<StorageReference> = result.result!!.items
//
//            item.forEachIndexed { index, storageReference ->
//                storageReference.downloadUrl.addOnSuccessListener {
//                    Log.d("bookOfCovidDataClass","$it")
//                    uriLiveDataForVideos.postValue(bookOfCovidDataClassVideos(it.toString()))
//                }.addOnCompleteListener{
//                    userLiveDataSuccessful.postValue("Successfully loaded")
//
//                }.addOnFailureListener{
//                    userLiveDataError.postValue("failed to load")
//
//
//                }
//
//            }
//        }
//
//    }
}