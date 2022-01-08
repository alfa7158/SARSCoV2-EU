package com.example.covideu.repostries

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.database.DatabaseReference




class bookOfCovidFireBaseRepository {
    val storage = FirebaseStorage.getInstance()
    //
    fun uploadBookOfCovidContent() = FirebaseStorage.getInstance().getReference("File uploaded")
    fun uploadVideos(uid:String) = FirebaseStorage.getInstance().getReference("Videos uploaded/$uid")
    fun uploadAudio(uid:String) = FirebaseStorage.getInstance().getReference("Audio uploaded/$uid")
    fun uploadPdf(uid:String) = FirebaseStorage.getInstance().getReference("Pdf uploaded/$uid")
    fun uploadDocx(uid:String) = FirebaseStorage.getInstance().getReference("Docx uploaded/$uid")
    fun uploadPictures(uid:String) = FirebaseStorage.getInstance().getReference("Pictures uploaded/$uid")
   // fun fetch() =   storage.reference.child("bookOfCovidImages")
   fun deleteImage(imageUri:String) = FirebaseStorage.getInstance().getReferenceFromUrl(imageUri)
//    var storageReference = FirebaseStorage.getInstance().getReference("newFolder/$userUid")
    ///////////////////////////recyclers reference/////////////////////////////////

    fun getPhotosBookOfCovid(uid:String)=  storage.reference.child("Pictures uploaded/$uid")
   fun getVideosBookOfCovid(uid:String)=  storage.reference.child("Videos uploaded/$uid")
   fun getAudioBookOfCovid(uid:String)=  storage.reference.child("Audio uploaded/$uid")
   fun getPdfOfCovid(uid:String)=  storage.reference.child("Pdf uploaded/$uid")



}