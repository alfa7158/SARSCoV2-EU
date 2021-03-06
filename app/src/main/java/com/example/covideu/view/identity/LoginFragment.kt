package com.example.covideu.view.identity

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.covideu.R
import com.google.firebase.auth.FirebaseAuth
import androidx.appcompat.app.AppCompatActivity

/**
 * This is the login class which is responsible for handling the user login
 */


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
        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()

        sharedPref = requireActivity().getSharedPreferences(SHARED_PREF_FILE, Context.MODE_PRIVATE)
        sharedPreferencesEditor = sharedPref.edit()
        return inflater.inflate(R.layout.fragment_login, container, false)

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

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

            /**
             * The if condition below is to check if user has an account or not and set shared
             * preference to ture if the user is logged in
             */

            if (email.isNotEmpty()&&password.isNotEmpty()){

                FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password)
                    .addOnCompleteListener(){
                        if(it.isSuccessful){


                            sharedPreferencesEditor.putBoolean("status",true)
                            sharedPreferencesEditor.putString("uid",FirebaseAuth.getInstance().currentUser?.uid)
                            sharedPreferencesEditor.commit()
                            Log.d(TAG,"user id:${sharedPref.getString("uid","")!!}")
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
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        val searchItem = menu.findItem(R.id.searchAction)

        searchItem.isVisible = false
    }


}
