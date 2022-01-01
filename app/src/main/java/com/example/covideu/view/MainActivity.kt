package com.example.covideu.view

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.content.pm.ActivityInfo
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.FragmentContainerView
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.covideu.R
import com.example.covideu.view.identity.SHARED_PREF_FILE
import com.google.firebase.auth.FirebaseAuth

private const val TAG = "Main_Activity"
class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var user: FirebaseAuth
    private lateinit var sharedPref: SharedPreferences
    private lateinit var sharedPreferencesEditor: SharedPreferences.Editor
    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation =  (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
        setContentView(R.layout.activity_main)
        ApiRepositoryCovidData.init()
        navController = (supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment).navController

        sharedPref = getSharedPreferences(SHARED_PREF_FILE, Context.MODE_PRIVATE)
        sharedPreferencesEditor =  sharedPref.edit()



    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.custom_menu,menu)
        return true

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
       // Log.d(com.example.covideu.view.Selection.mainSelect.TAG,"Inside the onOptionsItemSelected")
        when(item.itemId){
            R.id.item_logout ->{
               // Log.d(com.example.covideu.view.Selection.mainSelect.TAG,"Inside the item_logout")

                user = FirebaseAuth.getInstance()
                user.signOut()
                sharedPreferencesEditor.putBoolean("status",false).apply()

                navController.navigate(R.id.loginFragment2)
               // Log.d(com.example.covideu.view.Selection.mainSelect.TAG,"After the navigate")

            }

            R.id.item_profile ->{
                navController.navigate(R.id.updateProfileFragment2)


            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        if(navController.currentDestination?.label == "fragment_login"){
            finish()
        } else {
            super.onBackPressed()


        }

    }
}