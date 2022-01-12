package com.example.covideu.view.ViewModels.UserInfo

import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.covideu.database.profile.UserData
import com.example.covideu.repostries.userInfoRepository_Firebase
import com.google.firebase.database.*

class UserInfoViewModel: ViewModel() {


    private val repository = userInfoRepository_Firebase()
    val userLiveData = MutableLiveData<UserData>()
    val userLiveDataError = MutableLiveData<String>()
    val userLiveDataSuccessful = MutableLiveData<String>()

    private lateinit var user: UserData
    private lateinit var database: DatabaseReference



    fun UpdateUserInfo(uid:String){

        repository.UpdateUser(uid).addValueEventListener(object : ValueEventListener {
            @RequiresApi(Build.VERSION_CODES.P)
            override fun onDataChange(snapshot: DataSnapshot) {
                user = snapshot.getValue(UserData::class.java)!!

                userLiveData.postValue(user)

            }

            override fun onCancelled(error: DatabaseError) {
                userLiveDataError.postValue("failed")

            }

        })

    }

    fun deleteUserInfo(userName: String){

        ///val database = FirebaseDatabase.getInstance().reference
        val applesQuery: Query = repository.deleteUser(userName)

        applesQuery.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (appleSnapshot in dataSnapshot.children) {
                    appleSnapshot.ref.removeValue().addOnSuccessListener {
                        userLiveDataSuccessful.postValue("Successful deleted")


                    }.addOnFailureListener {
                        userLiveDataError.postValue(it.message)
                    }

                }
            }

            override fun onCancelled(error: DatabaseError) {
                userLiveDataError.postValue(error.message)
            }


        })


    }

    fun addUserInfo(uid: String,user: UserData){
        repository.addUserInfo(uid).setValue(user).addOnFailureListener {
            userLiveDataSuccessful.postValue("Successful added")
        }.addOnFailureListener {
            userLiveDataError.postValue(it.message)


        }

    }

     fun uploadImage(imageUri:Uri) {



        repository.uploadImage().putFile(imageUri).addOnSuccessListener {
            userLiveDataSuccessful.postValue("Successful uploaded")
        }.addOnFailureListener{

            userLiveDataError.postValue(it.message)
        }
    }








}