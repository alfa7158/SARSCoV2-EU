package com.example.covideu.view.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.covideu.R
import com.example.covideu.database.UserData
import com.example.covideu.databinding.FragmentUserInformationBinding
import com.example.covideu.view.ViewModels.UserInfo.UserInfoViewModel
import com.google.firebase.auth.FirebaseAuth


class UserInformationFragment : Fragment() {
    private val userInfo_ViewMode: UserInfoViewModel by activityViewModels()

    private lateinit var binding: FragmentUserInformationBinding
    private lateinit var auth: FirebaseAuth

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
//        auth = FirebaseAuth.getInstance()
//        val uid = auth.currentUser?.uid
//        binding.saveBtn.setOnClickListener{
//            val firstName = binding.etFirstName.text.toString()
//            val lastName = binding.etLastName.text.toString()
//            val bio = binding.etBio.text.toString()
//            val user = UserData(firstName,lastName,bio)
//
//            if(uid !=null ){
//
//                userInfo_ViewMode.addUserInfo(uid,user)
//                checkForSuccessful()
//                Toast.makeText(context, "added successfully", Toast.LENGTH_SHORT).show()
//
//            }else{
//                checkForError()
//                Toast.makeText(context, "Failed to add", Toast.LENGTH_SHORT).show()
//            }
//            //findNavController().navigate(R.id.action_registerFragment_to_userInformationFragment)
//
//
//        }
//
//
//
//
//
//
//
//    }
//    fun checkForSuccessful(){
//        userInfo_ViewMode.userLiveDataSuccessful.observe(viewLifecycleOwner,{
//
//            Toast.makeText(context, "deletion was Successful", Toast.LENGTH_SHORT).show()
//
//        })
//
//
//
//    }
//    fun checkForError(){
//
//        userInfo_ViewMode.userLiveDataError.observe(viewLifecycleOwner,{
//
//            Toast.makeText(context, "Failed to delete", Toast.LENGTH_SHORT).show()
//
//        })
    }

}