package com.example.covideu.view.identity

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.covideu.R
import com.google.firebase.auth.FirebaseAuth

const val SHARED_PREF_FILE = "theUser"

private const val TAG = "LoginFragment"
class LoginFragment : Fragment() {

    private lateinit var sharedPref: SharedPreferences
    private lateinit var sharedPreferencesEditor: SharedPreferences.Editor

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        sharedPref = requireActivity().getSharedPreferences(SHARED_PREF_FILE, Context.MODE_PRIVATE)
        sharedPreferencesEditor = sharedPref.edit()
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {



        super.onViewCreated(view, savedInstanceState)

        val loginEmail: EditText = view.findViewById(R.id.loginEmailEditText)
        val loginPassword: EditText = view.findViewById(R.id.loginPasswordEditeText)
        val loginButton: Button = view.findViewById(R.id.loginButton)
        val loginRegisterButton: Button = view.findViewById(R.id.registerLoginButton)

        val status = sharedPref.getBoolean("status", false)
        if(status)
        {
            findNavController().navigate(R.id.action_loginFragment2_to_mainSelectFragment)
        }

        loginRegisterButton.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment2_to_registerFragment)

        }

        loginButton.setOnClickListener{
            val email:String = loginEmail.text.toString()
            val password:String = loginPassword.text.toString()



            if (email.isNotEmpty()&&password.isNotEmpty()){

                FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password)
                    .addOnCompleteListener(){
                        if(it.isSuccessful){


                            sharedPreferencesEditor.putBoolean("status",true).commit()
                            Log.d(TAG,"${sharedPref.getBoolean("status",false)}")


                            parentFragmentManager.popBackStack()

                            findNavController().navigate(R.id.action_loginFragment2_to_mainSelectFragment)



                        }else{

                            Toast.makeText(context, it.exception!!.message.toString(), Toast.LENGTH_SHORT).show()

                        }

                    }




            }

        }




    }



}
