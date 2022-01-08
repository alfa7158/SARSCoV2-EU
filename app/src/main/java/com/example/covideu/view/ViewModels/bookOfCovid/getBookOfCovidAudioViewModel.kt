package com.example.covideu.view.ViewModels.bookOfCovid

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.covideu.database.bookOfCovidDataClassAudio
import com.example.covideu.database.bookOfCovidDataClassPhotos
import com.example.covideu.repostries.bookOfCovidFireBaseRepository
import com.google.android.gms.tasks.Task
import com.google.firebase.storage.ListResult
import com.google.firebase.storage.StorageReference

class getBookOfCovidAudioViewModel:ViewModel() {
    private val bookOfCoivdRepository = bookOfCovidFireBaseRepository()

    val userLiveDataError = MutableLiveData<String>()
    val userLiveDataSuccessful = MutableLiveData<String>()
        val uriLiveDataForAudio = MutableLiveData<bookOfCovidDataClassAudio>()

    fun getBookOfCovidAudio(uid: String){

//        val storageRef = storage.reference.child("bookOfCoivdImages")
//        val imageList:MutableLiveData<bookOfCovidDataClass> = MutableLiveData<bookOfCovidDataClass>()

        val listAllTask: Task<ListResult> = bookOfCoivdRepository.getAudioBookOfCovid(uid).listAll().addOnCompleteListener { result ->
            val item:List<StorageReference> = result.result!!.items

            item.forEachIndexed { index, storageReference ->
                storageReference.downloadUrl.addOnSuccessListener {
                    Log.d("bookOfCovidDataClass","$it")
                    uriLiveDataForAudio.postValue(bookOfCovidDataClassAudio(it.toString()))
                }.addOnCompleteListener{
                    userLiveDataSuccessful.postValue("Successfully loaded")

                }.addOnFailureListener{
                    userLiveDataError.postValue("failed to load")


                }

            }
        }

    }
}