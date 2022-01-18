package com.example.covideu.view.ViewModels.bookOfCovidViewModels

import android.util.Log
import android.widget.Toast
import androidx.core.content.contentValuesOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.covideu.database.bookOfCovid.*
import com.example.covideu.repostries.bookOfCovidFireBaseRepository

/**
 * This class holds the delete function that related to the book of covid
 */
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
    /**
     * The function below is responsible for deleting an image from Firestore
     */
    fun deleteAnImage(bookOfCovidDataClassPhotos: bookOfCovidDataClassPhotos) {
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
    /**
     * The function below is responsible for deleting an video from Firestore
     */
    fun deleteVideo(bookOfCovidDataClassVideos: bookOfCovidDataClassVideos) {
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
    /**
     * The function below is responsible for deleting an audio from Firestore
     */
    fun deleteAnAudio(bookOfCovidDataClassAudio: bookOfCovidDataClassAudio) {
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
    /**
     * The function below is responsible for deleting an pdf from Firestore
     */
    fun deletePdf(bookOfCovidDataClassPdf: bookOfCovidDataClassPdf) {
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
    /**
     * The function below is responsible for deleting a docx from Firestore
     */
    fun deleteDocx(bookOfCovidDataClassDocx: bookOfCovidDataClassDocx) {
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
    /**
     * The function below is responsible for deleting a favorite from Firestore
     */
    fun deleteFavorite(imageId:String,user:String){
         bookOfCoivdRepository.deleteFavoriteFireStore(imageId,user)

    }
    /**
     * The function below is responsible for deleting an comments from Firestore
     */
//    fun deleteComment(BookOfCovidDataClassComments: BookOfCovidDataClassComments){
//        var myList = mutableListOf<BookOfCovidDataClassFavorite>()
//        var imageSnapShot = bookOfCoivdRepository.deleteCommentsFireStore(BookOfCovidDataClassComments).get()
//            .addOnSuccessListener { result ->
//                for (document in result) {
//                    bookOfCoivdRepository.commentsCollection.document(document.id).delete().addOnSuccessListener {
//                        userLiveDataSuccessful.postValue("Successful")
//                    }.addOnFailureListener{
//                        userLiveDataError.postValue("failed to delete")
//
//                    }
//                }
//
//            }
//            .addOnFailureListener { exception ->
//                Log.w(TAG, "Error getting documents.", exception)
//            }
//    }
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