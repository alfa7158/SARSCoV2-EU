package com.example.covideu.view.bookOfCovid

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.example.covideu.R
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference


class DeletePhotosFragment : Fragment() {


//    val storage = FirebaseStorage.getInstance()
//    lateinit var uri:String
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_delete_photos, container, false)
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
////        val storageRef = storage.reference
//
//        val deleteButton:Button = view.findViewById(R.id.storageDelete)
//
//        deleteButton.setOnClickListener {
////            deleteImage()
//
//        }
//    }

//    fun deleteImage(){
//    uri = "https://firebasestorage.googleapis.com/v0/b/covidutd-2ad5a.appspot.com/o/bookOfCoivdImages%2FAsia.png?alt=media&token=7f2e554a-2a72-43d2-8cb1-fe867b9066a5"
//    var reference:StorageReference = FirebaseStorage.getInstance().getReferenceFromUrl(uri)
//
//        reference.delete().addOnSuccessListener  {
//
//            Toast.makeText(context, "Deleted successfully ", Toast.LENGTH_SHORT).show()
//        }.addOnFailureListener{
//
//            Toast.makeText(context, "failed to delete", Toast.LENGTH_SHORT).show()
//        }
//    }
}