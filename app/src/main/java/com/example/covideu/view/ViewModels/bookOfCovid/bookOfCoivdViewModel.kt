package com.example.covideu.view.ViewModels.bookOfCovid

import android.net.Uri
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.covideu.database.bookOfCovidDataClassPhotos
import com.example.covideu.database.bookOfCovidDataClassVideos
import com.example.covideu.repostries.bookOfCovidFireBaseRepository
import com.google.android.gms.tasks.Task
import com.google.firebase.storage.ListResult
import com.google.firebase.storage.StorageReference
import java.lang.Exception

class bookOfCoivdViewModel:ViewModel() {
    private val bookOfCoivdRepository = bookOfCovidFireBaseRepository()
    val userLiveDataError = MutableLiveData<String>()
    val userLiveDataSuccessful = MutableLiveData<String>()
    val uriLiveDataForVideos = MutableLiveData<bookOfCovidDataClassVideos>()
//    val uriLiveDataForPdf = MutableLiveData<bookOfCovidDataClassPhotos>()
//    val uriLiveDataForAudio = MutableLiveData<bookOfCovidDataClassPhotos>()
//    val uriLiveDataForDocx = MutableLiveData<bookOfCovidDataClassPhotos>()
    val uriLiveDataForPhotos = MutableLiveData<bookOfCovidDataClassPhotos>()

//    fun uploadContentBookOfCovid(uri:Uri){
//      var reference = uri.lastPathSegment?.let { bookOfCoivdRepository.uploadBookOfCovidContent().child(it) }
//         reference?.putFile(uri)?.addOnCanceledListener {
//             userLiveDataSuccessful.postValue("Successfully uploaded")
//         }?.addOnFailureListener{
//             userLiveDataError.postValue("Failed to upload file")
//
//
//         }
//    }

    fun uploadVideos(uri: Uri, uid: String){
      var reference = uri.lastPathSegment?.let { bookOfCoivdRepository.uploadVideos(uid).child(it) }
         reference?.putFile(uri)?.addOnCanceledListener {
             userLiveDataSuccessful.postValue("Successfully uploaded")
         }?.addOnFailureListener{
             userLiveDataError.postValue("Failed to upload file")


         }
    }

    fun uploadAudio(uri: Uri, uid: String){
      var reference = uri.lastPathSegment?.let { bookOfCoivdRepository.uploadAudio(uid).child(it) }
         reference?.putFile(uri)?.addOnCanceledListener {
             userLiveDataSuccessful.postValue("Successfully uploaded")
         }?.addOnFailureListener{
             userLiveDataError.postValue("Failed to upload file")


         }
    }

    fun uploadPdf(uri: Uri, uid: String){
      var reference = uri.lastPathSegment?.let { bookOfCoivdRepository.uploadPdf(uid).child(it) }
         reference?.putFile(uri)?.addOnCanceledListener {
             userLiveDataSuccessful.postValue("Successfully uploaded")
         }?.addOnFailureListener{
             userLiveDataError.postValue("Failed to upload file")


         }
    }

    fun uploadDocx(uri: Uri, uid: String){
      var reference = uri.lastPathSegment?.let { bookOfCoivdRepository.uploadDocx(uid).child(it) }
         reference?.putFile(uri)?.addOnCanceledListener {
             userLiveDataSuccessful.postValue("Successfully uploaded")
         }?.addOnFailureListener{
             userLiveDataError.postValue("Failed to upload file")


         }
    }

    fun uploadPicture(uri: Uri, uid: String,time:Long){
      var reference = uri.lastPathSegment?.let { bookOfCoivdRepository.uploadPictures(uid).child(it) }
         reference?.putFile(uri)?.addOnCanceledListener {
             userLiveDataSuccessful.postValue("Successfully uploaded")
         }?.addOnFailureListener{
             userLiveDataError.postValue("Failed to upload file")


         }
    }

//    fun deleteAnImage(ImageUri:String){
//        try {
//            bookOfCoivdRepository.deleteImage(ImageUri).delete()
//            userLiveDataSuccessful.postValue("Successfully deleted")
//
//        }catch (e:Exception){
//            userLiveDataError.postValue("Failed to delete file")
//
//        }
//
//    }


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
//                    uriLiveDataForPhotos.postValue(bookOfCovidDataClassPhotos(it.toString()))
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

    fun getBookOfCovidAudio(uid: String){

//        val storageRef = storage.reference.child("bookOfCoivdImages")
//        val imageList:MutableLiveData<bookOfCovidDataClass> = MutableLiveData<bookOfCovidDataClass>()

        val listAllTask: Task<ListResult> = bookOfCoivdRepository.getAudioBookOfCovid(uid).listAll().addOnCompleteListener { result ->
            val item:List<StorageReference> = result.result!!.items

            item.forEachIndexed { index, storageReference ->
                storageReference.downloadUrl.addOnSuccessListener {
                    Log.d("bookOfCovidDataClass","$it")
                    uriLiveDataForPhotos.postValue(bookOfCovidDataClassPhotos(it.toString()))
                }.addOnCompleteListener{
                    userLiveDataSuccessful.postValue("Successfully loaded")

                }.addOnFailureListener{
                    userLiveDataError.postValue("failed to load")


                }

            }
        }

    }


    }





