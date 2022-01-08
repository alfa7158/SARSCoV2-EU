package com.example.covideu.view.profile

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.liveData
import com.example.covideu.R
import com.example.covideu.databinding.FragmentUpdateProfileBinding
import com.example.covideu.databinding.FragmentUploadImageBinding
import com.example.covideu.view.ViewModels.UserInfo.UserInfoViewModel
import com.google.firebase.storage.FirebaseStorage
import java.net.URI
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*
import java.util.logging.SimpleFormatter

private const val TAG = "uploadImageFragment"
class uploadImageFragment : Fragment() {
     var imageUri:Uri? = null
    lateinit var binding:FragmentUploadImageBinding
    private val UploadUserImage_ViewMode: UserInfoViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity?)!!.supportActionBar!!.show()

        // Inflate the layout for this fragment
        binding = FragmentUploadImageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)




        binding.chooseAnImageButton.setOnClickListener{

            selectImage()

        }

        binding.uploadAnImageButton.setOnClickListener {
            imageUri?.let {

                UploadUserImage_ViewMode.uploadImage(it)
//                val bundle = Bundle()
//
//                bundle.putString("theImage", imageUri?.toString()?.toUri().toString())


            }


        }


        checkForSuccessful()


    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode==1&&resultCode==RESULT_OK){

            imageUri = data?.data!!
            Log.d(TAG,"theImage${imageUri}")
            binding.uploadImageView.setImageURI(imageUri)


        }else{

            checkForError()
        }
    }

//    private fun uploadImage() {
//
//        val theFormat = SimpleDateFormat("yyyy_MM_dd_HH_mm_ss",Locale.getDefault())
//
//        val now = Date()
//        val fileName = theFormat.format(now)
//
//        val storageReference = FirebaseStorage.getInstance().getReference("image/$fileName")
//
//        storageReference.putFile(imageUri).addOnSuccessListener {
//            binding.uploadImageView.setImageURI(null)
//            Toast.makeText(context, "image was uploaded successfully", Toast.LENGTH_SHORT).show()
//        }.addOnFailureListener{
//
//            Toast.makeText(context, "failed to upload", Toast.LENGTH_SHORT).show()
//        }
//    }

    private fun selectImage() {

    val intent = Intent()

        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT

        startActivityForResult(intent,1)


    }



    fun checkForSuccessful(){
        UploadUserImage_ViewMode.userLiveDataSuccessful.observe(viewLifecycleOwner,{

            Toast.makeText(context, "Successful uploaded", Toast.LENGTH_SHORT).show()
                binding.uploadImageView.setImageURI(null)

        })



    }
    fun checkForError(){

        UploadUserImage_ViewMode.userLiveDataError.observe(viewLifecycleOwner,{

            Toast.makeText(context, "Failed to upload image", Toast.LENGTH_SHORT).show()

        })
    }





}