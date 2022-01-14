package com.example.covideu.view.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.covideu.databinding.FragmentDeleteProfileBinding
import com.example.covideu.view.ViewModels.UserInfoViewModel.UserInfoViewModel


class DeleteProfileFragment : Fragment() {
    private lateinit var binding:FragmentDeleteProfileBinding

    private val userInfo_ViewMode: UserInfoViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDeleteProfileBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.deletebtn.setOnClickListener{

            var email = binding.etuserName.text.toString()
            if(email.isNotEmpty()){
                userInfo_ViewMode.deleteUserInfo(email)
                checkForSuccessful()

            }else{
                checkForError()
                Toast.makeText(context, "please Enter a value", Toast.LENGTH_SHORT).show()
            }
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