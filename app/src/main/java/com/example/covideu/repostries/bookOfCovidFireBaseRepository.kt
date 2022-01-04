package com.example.covideu.repostries

import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class bookOfCovidFireBaseRepository {
    val storage = FirebaseStorage.getInstance()
    //

    fun uploadBookOfCovidContent() = FirebaseStorage.getInstance().getReference("File uploaded")
    fun uploadVideos() = FirebaseStorage.getInstance().getReference("Videos uploaded")
    fun uploadAudio() = FirebaseStorage.getInstance().getReference("Audio uploaded")
    fun uploadPdf() = FirebaseStorage.getInstance().getReference("Pdf uploaded")
    fun uploadDocx() = FirebaseStorage.getInstance().getReference("Docx uploaded")
    fun uploadPictures() = FirebaseStorage.getInstance().getReference("Pictures uploaded")
   // fun fetch() =   storage.reference.child("bookOfCovidImages")
   fun deleteImage(imageUri:String) = FirebaseStorage.getInstance().getReferenceFromUrl(imageUri)

    ///////////////////////////recyclers reference/////////////////////////////////
   fun getPhotosBookOfCovid()=  storage.reference.child("Pictures uploaded")
   fun getVideosBookOfCovid()=  storage.reference.child("Videos uploaded")
   fun getAudioBookOfCovid()=  storage.reference.child("Audio uploaded")
   fun getPdfOfCovid()=  storage.reference.child("Pdf uploaded")
   fun getDocxOfCovid()=  storage.reference.child("Docx uploaded")




}