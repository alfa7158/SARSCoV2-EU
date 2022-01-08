package com.example.covideu.view.splash

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.example.covideu.R
import com.example.covideu.view.MainActivity
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

const val SHARED_PREF_FILE = "theUser"


class SplashFragment() : Fragment() {

//    private lateinit var sharedPref: SharedPreferences


//    override val coroutineContext: CoroutineContext
//        get() = Dispatchers.Main + Job()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()


        // Timer for splash
        object : CountDownTimer(2000,1000){
            override fun onTick(p0: Long) {

            }

            override fun onFinish() {
                findNavController().navigate(R.id.action_splashFragment_to_loginFragment23)

            }

        }.start()

//        if (status) {
//            findNavController().navigate(R.id.action_splashFragment_to_mainSelectFragment)
//
//
//        } else {
//            findNavController().navigate(R.id.action_splashFragment_to_loginFragment23)
//
//
//        }




//        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()
//        launch {
//            delay(2500)
//            withContext(Dispatchers.Main) {
//                (activity as MainActivity)
//            }
//            findNavController().navigate(R.id.action_splashFragment_to_loginFragment23)
//
//
//        }


    }
}






