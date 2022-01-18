package com.example.covideu.view.ViewModels.bookOfCovidViewModels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.covideu.database.bookOfCovid.*
import com.example.covideu.repostries.bookOfCovidFireBaseRepository
import com.google.firebase.firestore.ktx.toObject
/**
 * This class hold a function for getting the photos
 */
private const val TAG = "getBookOfCovidPhotosVie"
class getBookOfCovidPhotosViewModel:ViewModel() {

    private val bookOfCoivdRepository = bookOfCovidFireBaseRepository()
    val userLiveDataError = MutableLiveData<String>()
    val userLiveDataSuccessful = MutableLiveData<String>()
    val uriLiveDataForPhotos = MutableLiveData<List<bookOfCovidDataClassPhotos>>()
    val uriLiveDataForPhotosDetail = MutableLiveData<bookOfCovidDataClassPhotos>()


    /**
     * The function below job is to get the photos from the firebase
     */
    fun getBookOfCovidPhotos(){
        var photoList = mutableListOf<bookOfCovidDataClassPhotos>()
        var imageSnapShot = bookOfCoivdRepository.getPhotosBookOfCovid().get()
            .addOnSuccessListener { result ->
                for (document in result) {
                   var x = document.toObject<bookOfCovidDataClassPhotos>()
                    x.elementId = document.id
                    photoList.add(x)

                    Log.d(TAG, "${document.id} => ${document.data}")


                }

                uriLiveDataForPhotos.postValue(photoList)


            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents.", exception)
            }
    }










    ///fireStorage get method
//    fun getBookOfCovidPhotos(uid: String){
//
////        val storageRef = storage.reference.child("bookOfCoivdImages")
////        val imageList:MutableLiveData<bookOfCovidDataClass> = MutableLiveData<bookOfCovidDataClass>()
//
//        val listAllTask: Task<ListResult> = bookOfCoivdRepository.getPhotosBookOfCovid(uid).listAll().addOnCompleteListener { result ->
//            val item:List<StorageReference> = result.result!!.items
//
//            item.forEachIndexed { index, storageReference ->
//                storageReference.downloadUrl.addOnSuccessListener {
//                    Log.d("bookOfCovidDataClass","$it")
//                    uriLiveDataForPhotos.postValue(bookOfCovidDataClassPhotos(it.toString(),uid))
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