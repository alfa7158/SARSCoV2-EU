package com.example.covideu.database.reoprtCases

/**
 * This class is report covid-19 cases data class which is used in firestore
 */
data class ReportCasesDataClass(var uid:String ?="",var idType:String ?="", var id: String ?="", val lan:Double?=0.0, var lat:Double ?=0.0, var numberOfInfected:Int ?= 0,var relationship:String ?="",var address:String)
