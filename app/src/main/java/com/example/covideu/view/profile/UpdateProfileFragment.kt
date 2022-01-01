package com.example.covideu.view.profile

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
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
    private lateinit var gender:String

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

    @SuppressLint("CutPasteId")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.UpdateProfileImageView.setOnClickListener {

            findNavController().navigate(R.id.action_updateProfileFragment2_to_uploadImageFragment)
        }
        Glide.with(requireContext())
            .load("https://firebasestorage.googleapis.com/v0/b/covidutd-2ad5a.appspot.com/o/${FirebaseAuth.getInstance().uid.toString()}?alt=media&token=59881b60-98db-4dd8-a446-b20837e9a576")
            .diskCacheStrategy(DiskCacheStrategy.NONE )
            .skipMemoryCache(true)
            .into(binding.UpdateProfileImageView)


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
            val lastName = binding.lastNameEditTextUpdate.text.toString()
            val age = binding.ageEditTextUpdate.text.toString()
            val occupation = binding.occupationEditTextUpdate.text.toString()
            val genderRadioGroup = binding.genderRadioGroupUpdate
            val selectedGenderRadioButton:RadioButton = view.findViewById(genderRadioGroup.checkedRadioButtonId)
            gender = selectedGenderRadioButton.text.toString()
            val user = UserData(firstName, lastName,age,gender,occupation)


            databaseReference.child(uid).setValue(user)


        }

    }

    fun getUserData(){
        userInfo_ViewMode.userLiveData.observe(viewLifecycleOwner,{

            var maleSelect = binding.maleRadioButtonUpdate
            var femaleSelect = binding.femaleRadioButtonUpdate

            user = it
            binding.firstNameUpdate.setText(user.firstName)
            binding.lastNameEditTextUpdate.setText(user.lastName)
            binding.ageEditTextUpdate.setText(user.age)
            binding.occupationEditTextUpdate.setText(user.occupation)
            if(user.gender=="Male"){
                maleSelect.isChecked = true

            }else if(user.gender=="Female") {

                femaleSelect.isChecked = true
            }












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