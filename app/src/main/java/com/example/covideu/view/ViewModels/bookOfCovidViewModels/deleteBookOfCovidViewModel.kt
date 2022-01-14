package com.example.covideu.view.ViewModels.bookOfCovidViewModels

import android.util.Log
import android.widget.Toast
import androidx.core.content.contentValuesOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.covideu.database.bookOfCovid.*
import com.example.covideu.repostries.bookOfCovidFireBaseRepository

private const val TAG = "deleteBookOfCovidViewMo"
class deleteBookOfCovidViewModel:ViewModel() {

    private val bookOfCoivdRepository = bookOfCovidFireBaseRepository()
    val userLiveDataError = MutableLiveData<String>()
    val userLiveDataSuccessful = MutableLiveData<String>()

    //    fun deleteAnImage(bookOfCovidDataClassPhotos: bookOfCovidDataClassPhotos) {
//        val imageQuery = bookOfCoivdRepository.deletePicturesFireStore()
//            .whereEqualTo("imageName", bookOfCovidDataClassPhotos.imageName)
//            .whereEqualTo("uid", bookOfCovidDataClassPhotos.uid).get().awa
//
//
//
//        bookOfCoivdRepository.deletePicturesFireStore().document(imageName).delete()
//            .addOnSuccessListener {
//                userLiveDataSuccessful.postValue("Successful")
//            }.addOnFailureListener {
//            userLiveDataError.postValue("Failed")
//
//        }
//    }
//    }
//
//    fun deleteAnImage(ImageUri:String){
//        try {
//            bookOfCoivdRepository.deleteImage(ImageUri).delete()
//            userLiveDataSuccessful.postValue("Successfully deleted")
//
//        }catch (e: Exception){
//            userLiveDataError.postValue("Failed to delete file")
//
//        }
//
//    }
    fun deleteAnImage(bookOfCovidDataClassPhotos: bookOfCovidDataClassPhotos) {
        var myList = mutableListOf<bookOfCovidDataClassPhotos>()
        var imageSnapShot =
            bookOfCoivdRepository.deletePicturesFireStore(bookOfCovidDataClassPhotos).get()
                .addOnSuccessListener { result ->
                    for (document in result) {
                        bookOfCoivdRepository.picturesCollection.document(document.id).delete()
                    }

                }
                .addOnFailureListener { exception ->
                    Log.w(TAG, "Error getting documents.", exception)
                }
    }

    fun deleteVideo(bookOfCovidDataClassVideos: bookOfCovidDataClassVideos) {
        var myList = mutableListOf<bookOfCovidDataClassVideos>()
        var imageSnapShot =
            bookOfCoivdRepository.deleteVideoFireStore(bookOfCovidDataClassVideos).get()
                .addOnSuccessListener { result ->
                    for (document in result) {
                        bookOfCoivdRepository.videoCollection.document(document.id).delete()
                    }

                }
                .addOnFailureListener { exception ->
                    Log.w(TAG, "Error getting documents.", exception)
                }
    }

    fun deleteAnAudio(bookOfCovidDataClassAudio: bookOfCovidDataClassAudio) {
        var myList = mutableListOf<bookOfCovidDataClassAudio>()
        var imageSnapShot =
            bookOfCoivdRepository.deleteAudioFireStore(bookOfCovidDataClassAudio).get()
                .addOnSuccessListener { result ->
                    for (document in result) {
                        bookOfCoivdRepository.audioCollection.document(document.id).delete()
                    }

                }
                .addOnFailureListener { exception ->
                    Log.w(TAG, "Error getting documents.", exception)
                }
    }

    fun deletePdf(bookOfCovidDataClassPdf: bookOfCovidDataClassPdf) {
        var myList = mutableListOf<bookOfCovidDataClassPdf>()
        var imageSnapShot = bookOfCoivdRepository.deletePdfFireStore(bookOfCovidDataClassPdf).get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    bookOfCoivdRepository.pdfCollection.document(document.id).delete()
                }

            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents.", exception)
            }
    }

    fun deleteDocx(bookOfCovidDataClassDocx: bookOfCovidDataClassDocx) {
        var myList = mutableListOf<bookOfCovidDataClassDocx>()
        var imageSnapShot =
            bookOfCoivdRepository.deleteDocxFireStore(bookOfCovidDataClassDocx).get()
                .addOnSuccessListener { result ->
                    for (document in result) {
                        bookOfCoivdRepository.docxCollection.document(document.id).delete()
                    }

                }
                .addOnFailureListener { exception ->
                    Log.w(TAG, "Error getting documents.", exception)
                }
    }

    fun deleteFavorite(bookOfCovidDataClassFavorite: BookOfCovidDataClassFavorite){
        var myList = mutableListOf<BookOfCovidDataClassFavorite>()
        var imageSnapShot = bookOfCoivdRepository.deleteFavoriteFireStore(bookOfCovidDataClassFavorite).get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    bookOfCoivdRepository.likesCollection.document(document.id).delete().addOnSuccessListener {

                    }.addOnFailureListener{


                    }
                }

            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents.", exception)
            }
    }    fun deleteComment(BookOfCovidDataClassComments: BookOfCovidDataClassComments){
        var myList = mutableListOf<BookOfCovidDataClassFavorite>()
        var imageSnapShot = bookOfCoivdRepository.deleteCommentsFireStore(BookOfCovidDataClassComments).get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    bookOfCoivdRepository.commentsCollection.document(document.id).delete().addOnSuccessListener {
                        userLiveDataSuccessful.postValue("Successful")
                    }.addOnFailureListener{
                        userLiveDataError.postValue("failed to delete")

                    }
                }

            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents.", exception)
            }
    }
//}    fun deleteFavorite(bookOfCovidDataClassFavorite: BookOfCovidDataClassFavorite){
//    var myList = mutableListOf<bookOfCovidDataClassPhotos>()
//    var imageSnapShot = bookOfCoivdRepository.deleteFavorite(bookOfCovidDataClassFavorite).get()
//        .addOnSuccessListener { result ->
//            for (document in result) {
//                    bookOfCoivdRepository.docxCollection.document(document.id).delete()
//            }
//
//        }
//        .addOnFailureListener { exception ->
//            Log.w(TAG, "Error getting documents.", exception)
//        }
//}




}