package com.example.covideu.view.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.covideu.R
import com.example.covideu.database.UserData
import com.example.covideu.databinding.FragmentUserInformationBinding
import com.example.covideu.view.ViewModels.UserInfo.UserInfoViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage


class UserInformationFragment : Fragment() {
    private val userInfo_ViewMode: UserInfoViewModel by activityViewModels()

    private lateinit var binding: FragmentUserInformationBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var gender:String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentUserInformationBinding.inflate(inflater,container,false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = FirebaseAuth.getInstance()
        val uid = auth.currentUser?.uid


        Glide.with(requireContext())
            .load("https://firebasestorage.googleapis.com/v0/b/covidutd-2ad5a.appspot.com/o/${FirebaseAuth.getInstance().uid.toString()}?alt=media&token=59881b60-98db-4dd8-a446-b20837e9a576")
            .diskCacheStrategy(DiskCacheStrategy.NONE )
            .skipMemoryCache(true)
            .into(binding.MainProfileImagwView)

        binding.MainProfileImagwView.setOnClickListener {

            findNavController().navigate(R.id.action_userInformationFragment_to_uploadImageFragment)
        }

        binding.saveProfile.setOnClickListener{
            val firstName = binding.firstNameProfile.text.toString()
            val lastName = binding.ProfilelastName.text.toString()
            val age = binding.ageProfile.text.toString()
            val genderRadioGroup = binding.genderRadioGroupInfo
            val selectedGenderRadioButton:RadioButton = view.findViewById(genderRadioGroup.checkedRadioButtonId)
            gender = selectedGenderRadioButton.text.toString()






            val occupation = binding.occupationEditText.text.toString()
            val user = UserData(firstName,lastName,age,gender,occupation)

            if(uid !=null ){

                userInfo_ViewMode.addUserInfo(uid,user)
                checkForSuccessful()
                Toast.makeText(context, "added successfully", Toast.LENGTH_SHORT).show()

            }else{
                checkForError()
                Toast.makeText(context, "Failed to add", Toast.LENGTH_SHORT).show()
            }

            findNavController().navigate(R.id.action_userInformationFragment_to_mainSelectFragment)
        }







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