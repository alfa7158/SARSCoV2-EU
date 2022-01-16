package com.example.covideu.view.ViewModels.bookOfCovidViewModels

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.covideu.database.bookOfCovid.*
import com.example.covideu.repostries.bookOfCovidFireBaseRepository
import com.google.firebase.auth.FirebaseAuth

/**
 * This class holds function that are responsible for upload data to the fire Storage and Firestore
 */
class bookOfCoivdViewModel:ViewModel() {
    private val bookOfCoivdRepository = bookOfCovidFireBaseRepository()
    val userLiveDataError = MutableLiveData<String>()
    val userLiveDataSuccessful = MutableLiveData<String>()
    val uriLiveDataForVideos = MutableLiveData<bookOfCovidDataClassVideos>()

    //    val uriLiveDataForPdf = MutableLiveData<bookOfCovidDataClassPhotos>()
//    val uriLiveDataForAudio = MutableLiveData<bookOfCovidDataClassPhotos>()
//    val uriLiveDataForDocx = MutableLiveData<bookOfCovidDataClassPhotos>()
//    val uriLiveDataForPhotos = MutableLiveData<bookOfCovidDataClassPhotos>()

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

    /**
     * The function below jobs os to upload picture to the firebase storage
     */
    fun uploadPictureStorage(uri: Uri, name: String,title:String,description:String){
        var reference = uri.lastPathSegment?.let { bookOfCoivdRepository.uploadPicturesStorage(name,title,description) }
        reference?.putFile(uri)?.addOnSuccessListener {
            userLiveDataSuccessful.postValue("Successfully uploaded")
        }?.addOnFailureListener{
            userLiveDataError.postValue("Failed to upload file")


        }
    }
    /**
     * The function below jobs os to upload videos to the firebase storage
     */
    fun uploadVideosStorage(uri: Uri, videoName: String,title:String,description:String) {
        var reference = uri.lastPathSegment?.let { bookOfCoivdRepository.uploadVideosStorage(videoName,title,description) }
        reference?.putFile(uri)?.addOnCanceledListener {
            userLiveDataSuccessful.postValue("Successfully uploaded")
        }?.addOnFailureListener {
            userLiveDataError.postValue("Failed to upload file")


        }
    }
    /**
     * The function below jobs os to upload audio to the firebase storage
     */
    fun uploadAudioStorage(uri: Uri, audioName: String,title:String,description:String) {
        var reference =
            uri.lastPathSegment?.let { bookOfCoivdRepository.uploadAudioStorage(audioName,title,description) }
        reference?.putFile(uri)?.addOnCanceledListener {
            userLiveDataSuccessful.postValue("Successfully uploaded")
        }?.addOnFailureListener {
            userLiveDataError.postValue("Failed to upload file")


        }
    }
    /**
     * The function below jobs os to upload pdf to the firebase storage
     */
    fun uploadPdfStorage(uri: Uri, pdfName: String,title:String,description:String) {
        var reference = uri.lastPathSegment?.let { bookOfCoivdRepository.uploadPdfStorage(pdfName,title,description) }
        reference?.putFile(uri)?.addOnCanceledListener {
            userLiveDataSuccessful.postValue("Successfully uploaded")
        }?.addOnFailureListener {
            userLiveDataError.postValue("Failed to upload file")


        }
    }
    /**
     * The function below jobs os to upload docx to the firebase storage
     */
    fun uploadDocxStorage(uri: Uri, docxName: String,title:String,description:String) {
        var reference = uri.lastPathSegment?.let { bookOfCoivdRepository.uploadDocxStorage(docxName,title,description) }
        reference?.putFile(uri)?.addOnCanceledListener {
            userLiveDataSuccessful.postValue("Successfully uploaded")
        }?.addOnFailureListener {
            userLiveDataError.postValue("Failed to upload file")


        }
    }


    /**
     * The function below jobs os to upload pictures to the firebase Firestore, with a title and
     * description
     */

    fun uploadPictureFireStore(uri: Uri, imageName: String,title: String,description: String) {
        uri.lastPathSegment?.let {
            bookOfCoivdRepository.uploadPicturesFireStore()
                .add(bookOfCovidDataClassPhotos(imageName,FirebaseAuth.getInstance().uid.toString(),title,description))
        }
            ?.addOnSuccessListener {
                uploadPictureStorage(uri,imageName,title,description)
                userLiveDataSuccessful.postValue("Successfully uploaded")


            }?.addOnFailureListener {
                userLiveDataError.postValue("Failed to upload file")


            }
    }
    /**
     * The function below jobs os to upload video to the firebase Firestore, with a title and
     * description
     */
    fun uploadVideoFireStore(uri: Uri, videoName: String,title: String,description: String) {
        uri.lastPathSegment?.let {
            bookOfCoivdRepository.uploadVideosFireStore()
                .add(bookOfCovidDataClassVideos(videoName,FirebaseAuth.getInstance().uid.toString(),title,description))
        }
            ?.addOnSuccessListener {
                uploadVideosStorage(uri,videoName,title,description)
                userLiveDataSuccessful.postValue("Successfully uploaded")


            }?.addOnFailureListener {
                userLiveDataError.postValue("Failed to upload file")


            }
    }
    /**
     * The function below jobs os to upload audio to the firebase Firestore, with a title and
     * description
     */
    fun uploadAudioFireStore(uri: Uri, audioName: String,title: String,description: String) {
        uri.lastPathSegment?.let {
            bookOfCoivdRepository.uploadAudioFireStore()
                .add(bookOfCovidDataClassAudio(audioName,FirebaseAuth.getInstance().uid.toString(),title,description))
        }
            ?.addOnSuccessListener {
                uploadAudioStorage(uri,audioName,title,description)
                userLiveDataSuccessful.postValue("Successfully uploaded")


            }?.addOnFailureListener {
                userLiveDataError.postValue("Failed to upload file")


            }
    }
    /**
     * The function below jobs os to upload pdf to the firebase Firestore, with a title and
     * description
     */
    fun uploadPdfFireStore(uri: Uri, pdfName: String,title: String,description: String) {
        uri.lastPathSegment?.let {
            bookOfCoivdRepository.uploadPdfFireStore()
                .add(bookOfCovidDataClassPdf(pdfName,FirebaseAuth.getInstance().uid.toString(),title,description))
        }
            ?.addOnSuccessListener {
                uploadPdfStorage(uri,pdfName,title,description)
                userLiveDataSuccessful.postValue("Successfully uploaded")


            }?.addOnFailureListener {
                userLiveDataError.postValue("Failed to upload file")


            }


    }
    /**
     * The function below jobs os to upload docx to the firebase Firestore, with a title and
     * description
     */
    fun uploadDocxFireStore(uri: Uri, docxName: String,title: String,description: String) {
        uri.lastPathSegment?.let {
            bookOfCoivdRepository.uploadDocxFireStore()
                .add(bookOfCovidDataClassDocx(docxName,FirebaseAuth.getInstance().uid.toString(),title,description))
        }
            ?.addOnSuccessListener {
                uploadDocxStorage(uri,docxName,title,description)
                userLiveDataSuccessful.postValue("Successfully uploaded")


            }?.addOnFailureListener {
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

//        fun getBookOfCovidAudio(uid: String) {
//
////        val storageRef = storage.reference.child("bookOfCoivdImages")
////        val imageList:MutableLiveData<bookOfCovidDataClass> = MutableLiveData<bookOfCovidDataClass>()
//
//            val listAllTask: Task<ListResult> =
//                bookOfCoivdRepository.getAudioBookOfCovid(uid).listAll()
//                    .addOnCompleteListener { result ->
//                        val item: List<StorageReference> = result.result!!.items
//
//                        item.forEachIndexed { index, storageReference ->
//                            storageReference.downloadUrl.addOnSuccessListener {
//                                Log.d("bookOfCovidDataClass", "$it")
//                                uriLiveDataForPhotos.postValue(bookOfCovidDataClassPhotos(it.toString(),uid))
//                            }.addOnCompleteListener {
//                                userLiveDataSuccessful.postValue("Successfully loaded")
//
//                            }.addOnFailureListener {
//                                userLiveDataError.postValue("failed to load")
//
//
//                            }
//
//                        }
//                    }
//
//        }


    }



