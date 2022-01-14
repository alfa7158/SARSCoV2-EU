package com.example.covideu.view.profile

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.covideu.R
import com.example.covideu.database.profile.UserData
import com.example.covideu.databinding.FragmentUserInformationBinding
import com.example.covideu.view.ViewModels.UserInfoViewModel.UserInfoViewModel
import com.google.firebase.auth.FirebaseAuth


class UserInformationFragment : Fragment() {
    private val userInfo_ViewMode: UserInfoViewModel by activityViewModels()

    private lateinit var binding: FragmentUserInformationBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var gender:String
    private lateinit var uid:String
    private lateinit var firstName:String
    private lateinit var lastName:String
    private lateinit var occupation:String
    private lateinit var age:String
    private lateinit var male:RadioButton
    private lateinit var female:RadioButton
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        (activity as AppCompatActivity?)!!.supportActionBar!!.show()

        binding = FragmentUserInformationBinding.inflate(inflater,container,false)
        return binding.root

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = FirebaseAuth.getInstance()
         uid = auth.currentUser?.uid.toString()


        Glide.with(requireContext())
            .load("https://firebasestorage.googleapis.com/v0/b/covidutd-2ad5a.appspot.com/o/profile%2f${FirebaseAuth.getInstance().uid.toString()}?alt=media&token=59881b60-98db-4dd8-a446-b20837e9a576")
            .diskCacheStrategy(DiskCacheStrategy.NONE )
            .skipMemoryCache(true)
            .placeholder(R.drawable.profimage)
            .into(binding.MainProfileImagwView)

        binding.MainProfileImagwView.setOnClickListener {

            findNavController().navigate(R.id.action_userInformationFragment_to_uploadImageFragment)
        }

        binding.saveProfile.setOnClickListener{


            firstName = binding.firstNameProfile.text.toString()

            lastName = binding.ProfilelastName.text.toString()
             age = binding.ageProfile.text.toString()
            val genderRadioGroup = binding.genderRadioGroupInfo

            val occupation = binding.occupationEditText.text.toString()
            male = binding.maleRadioButton
            female = binding.femaleRadioButton
           // val user = UserData(firstName,lastName,age,gender,occupation)



                if(firstName.isNotBlank()&&firstName.isNotEmpty()
                    &&lastName.isNotBlank()&&lastName.isNotEmpty()
                    &&occupation.isNotBlank()&&occupation.isNotEmpty()
                    &&(male.isChecked||female.isChecked)){
                    val selectedGenderRadioButton:RadioButton = view.findViewById(genderRadioGroup.checkedRadioButtonId)
                    gender = selectedGenderRadioButton.text.toString()
                    val user = UserData(firstName,lastName,age,gender,occupation)
                    if(uid !=null){

                        userInfo_ViewMode.addUserInfo(uid,user)


                        checkForSuccessful()
                        Toast.makeText(context, "added successfully", Toast.LENGTH_SHORT).show()
                        findNavController().navigate(R.id.action_userInformationFragment_to_mainSelectFragment)

                    }else{
                        checkForError()

                        Toast.makeText(context, "Failed to add", Toast.LENGTH_SHORT).show()
                    }

                }else{
                        Toast.makeText(context, "fill the values", Toast.LENGTH_SHORT).show()


                    Toast.makeText(context, "Please complete all your information", Toast.LENGTH_SHORT).show()

                }





        }







    }
    fun checkForSuccessful(){
        userInfo_ViewMode.userLiveDataSuccessful.observe(viewLifecycleOwner,{

            Toast.makeText(context, " Successful", Toast.LENGTH_SHORT).show()

        })



    }
    fun checkForError(){

        userInfo_ViewMode.userLiveDataError.observe(viewLifecycleOwner,{

            Toast.makeText(context, "Failed to delete", Toast.LENGTH_SHORT).show()

        })
    }

    override fun onPause() {
        super.onPause()

        userInfo_ViewMode.addUserInfo(uid, UserData("No first name","No last name","0","Male","No occupation"))

    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        val searchItem = menu.findItem(R.id.searchAction)

        searchItem.isVisible = false
    }
}