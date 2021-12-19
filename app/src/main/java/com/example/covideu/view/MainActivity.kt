package com.example.covideu.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.covideu.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ApiRepositoryCovidData.init()

    }
}