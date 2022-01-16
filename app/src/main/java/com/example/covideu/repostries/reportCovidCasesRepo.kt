package com.example.covideu.repostries

import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
/**
 * This is report cases Repository class which contains all methods that are related to reporting
 * covid-19 cases feature in the app. Firestore was used in the methods
 */
class reportCovidCasesRepo {

    val reportedCases = Firebase.firestore.collection("Reported Cases")
    fun addReportCases() = reportedCases






}