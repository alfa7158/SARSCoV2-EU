package com.example.covideu.repostries

import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class reportCovidCasesRepo {

    val reportedCases = Firebase.firestore.collection("Reported Cases")
    fun addReportCases() = reportedCases






}