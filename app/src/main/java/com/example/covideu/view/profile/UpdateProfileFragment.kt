package com.example.covideu.view.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.covideu.R
import com.example.covideu.database.UserData
import com.example.covideu.databinding.FragmentUpdateProfileBinding
import com.example.covideu.view.ViewModels.UserInfo.UserInfoViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UpdateProfileFragment : Fragment() {

    private lateinit var binding: FragmentUpdateProfileBinding
    private val userInfo_ViewMode: UserInfoViewModel by activityViewModels()

    private lateinit var auth: FirebaseAuth
    private lateinit var databaseReference: DatabaseReference
    private lateinit var user: UserData
    private lateinit var uid: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentUpdateProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        auth = FirebaseAuth.getInstance()
        uid = auth.currentUser?.uid.toString()
        databaseReference = FirebaseDatabase.getInstance().getReference("Users")
        getUserData()

        if (uid.isNotEmpty()){
            userInfo_ViewMode.UpdateUserInfo(uid)
            checkForSuccessful()

        }else{
            Toast.makeText(context, "failed to update", Toast.LENGTH_SHORT).show()
            checkForError()
        }

        binding.updateButton.setOnClickListener {

            val firstName = binding.firstNameUpdate.text.toString()
            val lastName = binding.lastNameUpdate.text.toString()
            val bio = binding.BioUpdate.text.toString()
            val user = UserData(firstName, lastName, bio)


            databaseReference.child(uid).setValue(user)


        }

    }

    fun getUserData(){
        userInfo_ViewMode.userLiveData.observe(viewLifecycleOwner,{

            user = it
            binding.firstNameUpdate.setText(user.firstName)
            binding.lastNameUpdate.setText(user.lastName)
            binding.BioUpdate.setText(user.bio)



        })



    }

    fun checkForSuccessful(){
        userInfo_ViewMode.userLiveDataSuccessful.observe(viewLifecycleOwner,{

            Toast.makeText(context, "deletion was Successful", Toast.LENGTH_SHORT).show()

        })



    }
    fun checkForError(){

        userInfo_ViewMode.userLiveDataError.observe(viewLifecycleOwner,{

            Toast.makeText(context, "Failed to delete", Toast.LENGTH_SHORT).show()

        })
    }


}