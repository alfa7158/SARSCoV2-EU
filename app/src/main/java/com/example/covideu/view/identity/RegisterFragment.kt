package com.example.covideu.view.identity

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.example.covideu.R
import com.example.covideu.util.RegisterValidation
import com.google.firebase.auth.FirebaseAuth


class RegisterFragment : Fragment() {
    private val validator = RegisterValidation()

    private lateinit var sharedPref: SharedPreferences
    private lateinit var sharedPreferencesEditor: SharedPreferences.Editor
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()
        sharedPref = requireActivity().getSharedPreferences(SHARED_PREF_FILE, Context.MODE_PRIVATE)
        sharedPreferencesEditor = sharedPref.edit()
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val registerEmail: EditText = view.findViewById(R.id.emialRegister)
        val registerPassword: EditText = view.findViewById(R.id.passwordRegister)
        val registerButton: Button = view.findViewById(R.id.registerButton)
        val confirmPassword: EditText = view.findViewById(R.id.confirmPasswordRegister)
        val getBackToLogin:TextView = view.findViewById(R.id.getBackButton)

        getBackToLogin.setOnClickListener {

            findNavController().navigate(R.id.action_registerFragment_to_loginFragment2)
        }
        registerButton.setOnClickListener {

            val email: String = registerEmail.text.toString()
            val password: String = registerPassword.text.toString()
            val confirmPassword:String = confirmPassword.text.toString()
            if (email.isNotBlank() &&password.isNotBlank() &&confirmPassword.isNotBlank()) {
                if(password==confirmPassword){

                    if (validator.emailIsValid(email)){

                        if(validator.passwordValid(password)){

                            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                                .addOnCompleteListener() {
                                    if (it.isSuccessful) {

                                        sharedPreferencesEditor.putBoolean("status",true).commit()
                                        sharedPreferencesEditor.putString("uid",FirebaseAuth.getInstance().currentUser?.uid)
                                        findNavController().navigate(R.id.action_registerFragment_to_userInformationFragment)


                                    }else{

                                        Toast.makeText(context, it.exception!!.message.toString(), Toast.LENGTH_SHORT).show()
                                    }

                        }
                    }else{

                        Toast.makeText(context, "Please check your password", Toast.LENGTH_SHORT).show()
                    }




                            }else{

                        Toast.makeText(context, "Please check your email ", Toast.LENGTH_SHORT).show()
                            }
                }else{

                    Toast.makeText(context, "passwords do not match", Toast.LENGTH_SHORT).show()
                }
//                FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
//                    .addOnCompleteListener() {
//                        if (it.isSuccessful) {
//                            findNavController().navigate(R.id.action_registerFragment_to_userInformationFragment)
//
//
//                        }


                    }


            }




        }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        val searchItem = menu.findItem(R.id.searchAction)

        searchItem.isVisible = false
    }



    }


