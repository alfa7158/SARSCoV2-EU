package com.example.covideu.repostries

import com.example.covideu.database.bookOfCovid.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

/**
 * This is book of covid Repository class which contains all methods that are related to book of
 * covid 19 feature in the app.  firebase storage and Firestore database are used for the method in
 * this repository
 */

class bookOfCovidFireBaseRepository {
    val storage = FirebaseStorage.getInstance()
    //
//    fun uploadBookOfCovidContent() = FirebaseStorage.getInstance().getReference("File uploaded")
    fun uploadVideosStorage(videoName: String,title:String,description:String) = FirebaseStorage.getInstance().getReference(videoName)
    fun uploadAudioStorage(audioName:String,title:String,description:String) = FirebaseStorage.getInstance().getReference(audioName)
    fun uploadPdfStorage(pdfName:String,title:String,description:String) = FirebaseStorage.getInstance().getReference(pdfName)
    fun uploadDocxStorage(docxName:String,title:String,description:String) = FirebaseStorage.getInstance().getReference(docxName)
    fun uploadPicturesStorage(imageName:String,title:String,description:String) = FirebaseStorage.getInstance().getReference(imageName)
   // fun fetch() =   storage.reference.child("bookOfCovidImages")
   fun deleteImage(imageUri:String) = FirebaseStorage.getInstance().getReferenceFromUrl(imageUri)
//    var storageReference = FirebaseStorage.getInstance().getReference("newFolder/$userUid")
    ///////////////////////////recyclers reference/////////////////////////////////

//    fun getPhotosBookOfCovid(uid:String)=  storage.reference.child("Pictures uploaded/$uid")
    fun getPhotosBookOfCovid()=  FirebaseFirestore.getInstance().collection("Pictures uploaded")
    fun getVideosBookOfCovid() = FirebaseFirestore.getInstance().collection("Videos uploaded")
    fun getAudioBookOfCovid() = FirebaseFirestore.getInstance().collection("Audio uploaded")
    fun getPdfBookOfCovid() = FirebaseFirestore.getInstance().collection("Pdf uploaded")
    fun getDocxBookOfCovid() = FirebaseFirestore.getInstance().collection("Docx uploaded")
    fun getLikesBookOfCovid() = FirebaseFirestore.getInstance().collection("Pictures uploaded").document().collection("likes")
    fun getCommentsBookOfCovid() = FirebaseFirestore.getInstance().collection("Pictures uploaded").document().collection("ImageId")

///////////////////////////////////////////////////////////////////////////////////////////


    //   fun getVideosBookOfCovid(uid:String)=  storage.reference.child("Videos uploaded/$uid")
  // fun getAudioBookOfCovid(uid:String)=  storage.reference.child("Audio uploaded/$uid")
//   fun getPdfOfCovid(uid:String)=  storage.reference.child("Pdf uploaded/$uid")
//

///////////////////////////////////FireStore/////////////////////////////////////////////////////
    val picturesCollection = Firebase.firestore.collection("Pictures uploaded")
    val videoCollection = Firebase.firestore.collection("Videos uploaded")
    val audioCollection = Firebase.firestore.collection("Audio uploaded")
    val pdfCollection = Firebase.firestore.collection("Pdf uploaded")
    val docxCollection = Firebase.firestore.collection("Docx uploaded")

    val likesCollection = Firebase.firestore.collection("Pictures uploaded").document().collection("likes")

    val commentsCollection = Firebase.firestore.collection("Pictures uploaded").document().collection("comments")


    fun uploadPicturesFireStore() = picturesCollection
    fun uploadVideosFireStore() = videoCollection
    fun uploadAudioFireStore() = audioCollection
    fun uploadPdfFireStore() = pdfCollection
    fun uploadDocxFireStore() = docxCollection
    fun addFavorite() = likesCollection
    fun addComments() = commentsCollection




    fun deletePicturesFireStore(bookOfCovidDataClassPhotos: bookOfCovidDataClassPhotos) = picturesCollection
        .whereEqualTo("imageName",bookOfCovidDataClassPhotos.imageName)

    fun deleteVideoFireStore(bookOfCovidDataClassVideos: bookOfCovidDataClassVideos) = videoCollection
        .whereEqualTo("videoName",bookOfCovidDataClassVideos.videoName)

    fun deleteAudioFireStore(bookOfCovidDataClassAudio: bookOfCovidDataClassAudio) = audioCollection
        .whereEqualTo("audioName",bookOfCovidDataClassAudio.audioName)


    fun deletePdfFireStore(bookOfCovidDataClassPdf: bookOfCovidDataClassPdf) = pdfCollection
        .whereEqualTo("pdfName",bookOfCovidDataClassPdf.pdfName)

    fun deleteDocxFireStore(bookOfCovidDataClassDocx: bookOfCovidDataClassDocx) = docxCollection
        .whereEqualTo("DocxName",bookOfCovidDataClassDocx.DocxName)

    fun deleteFavoriteFireStore(bookOfCovidDataClassFavorite: BookOfCovidDataClassFavorite) = likesCollection
        .whereEqualTo("ImageId",bookOfCovidDataClassFavorite.ImageId)

    fun deleteCommentsFireStore(bookOfCovidDataClassComments: BookOfCovidDataClassComments) = commentsCollection
        .whereEqualTo("ImageId",bookOfCovidDataClassComments.imageId)






}