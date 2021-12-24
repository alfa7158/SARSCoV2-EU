package com.example.covideu.view.identity

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.covideu.R
import com.google.firebase.auth.FirebaseAuth


class RegisterFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val registerEmail: EditText = view.findViewById(R.id.emialRegister)
        val registerPassword: EditText = view.findViewById(R.id.passwordRegister)
        val registerButton: Button = view.findViewById(R.id.registerButton)
        val confirmPassword: EditText = view.findViewById(R.id.confirmPasswordRegister)




        registerButton.setOnClickListener {

            val email: String = registerEmail.text.toString()
            val password: String = registerPassword.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {

                FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener() {
                        if (it.isSuccessful) {
                            //findNavController().navigate(R.id.action_registerFragment_to_searchPreferenceFragment)


                        } else {

                            Toast.makeText(
                                context,
                                it.exception!!.message.toString(),
                                Toast.LENGTH_SHORT
                            ).show()

                        }


                    }


            }




        }



    }


}