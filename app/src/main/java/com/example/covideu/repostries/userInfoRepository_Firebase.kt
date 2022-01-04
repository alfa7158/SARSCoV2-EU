package com.example.covideu.repostries

import android.net.Uri
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import java.text.SimpleDateFormat
import java.util.*

class userInfoRepository_Firebase {

    val theFormat = SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.getDefault())

    val now = Date()
    val fileName = theFormat.format(now)


    val database = FirebaseDatabase.getInstance().reference
    val databaseReference = FirebaseDatabase.getInstance().getReference("Users")
    fun addUserInfo(uid: String) = databaseReference.child(uid)
    fun deleteUser(userName:String) =  database.child("Users").orderByChild("lastName").equalTo(userName)
    fun UpdateUser(uid:String) = databaseReference.child(uid)
    fun uploadImage() = FirebaseStorage.getInstance().getReference(FirebaseAuth.getInstance().uid.toString())

}