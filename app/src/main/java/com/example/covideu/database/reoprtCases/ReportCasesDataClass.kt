package com.example.covideu.database.reoprtCases

data class ReportCasesDataClass(var uid:String, var id: Int ?=0, val lan:Double?=0.0, var lat:Double ?=0.0, var numberOfInfected:Int ?= 0,var relationship:String ?="")
