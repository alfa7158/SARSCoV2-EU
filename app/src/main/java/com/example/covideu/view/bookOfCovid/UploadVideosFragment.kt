package com.example.covideu.view.bookOfCovid

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.covideu.R
import com.example.covideu.databinding.FragmentUploadImageBinding
import com.example.covideu.databinding.FragmentUploadVideosBinding
import com.example.covideu.view.ViewModels.UserInfo.UserInfoViewModel
import com.example.covideu.view.ViewModels.bookOfCovid.bookOfCoivdViewModel

private const val TAG = "UploadVideosFragment"
class UploadVideosFragment : Fragment() {

    private val uploadContentViewModel: bookOfCoivdViewModel by activityViewModels()

    var PdfRequestCode:Int = 2
    var DocxRequestCode:Int = 3
    var AudioRequestCode:Int = 4
    var VideoRequestCode:Int = 5
    var picRequestCode:Int = 6




    var uri: Uri? = null
    lateinit var binding: FragmentUploadVideosBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentUploadVideosBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

            binding.uploadVideoButton.setOnClickListener{

                selectVideo()

            }

        binding.uplaodAudioButton.setOnClickListener {
            selectAudio()

        }

        binding.uploadPdfutton .setOnClickListener {
            selectPdf()
        }

        binding.UploadDocxButton.setOnClickListener {

            selectDocx()
        }

        binding.uploadAPicBookOfCovid.setOnClickListener {
            selectPic()

        }
    }


    private fun selectPic() {

        val intent = Intent()

        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT

        startActivityForResult(intent,picRequestCode)


    }






    private fun selectVideo() {

        val intent = Intent()

        intent.type = "video/*"
        intent.action = Intent.ACTION_GET_CONTENT

        startActivityForResult(intent,VideoRequestCode)


    }

    private fun selectPdf() {

        val intent = Intent()

        intent.type = "pdf/*"
        intent.action = Intent.ACTION_GET_CONTENT

        startActivityForResult(intent,PdfRequestCode)


    }

    private fun selectDocx() {

        val intent = Intent()

        intent.type = "docx/*"
        intent.action = Intent.ACTION_GET_CONTENT

        startActivityForResult(intent,DocxRequestCode)


    }

    private fun selectAudio() {

        val intent = Intent()

        intent.type = "audio/*"
        intent.action = Intent.ACTION_GET_CONTENT

        startActivityForResult(intent,AudioRequestCode)


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

         if (requestCode==PdfRequestCode&&resultCode== Activity.RESULT_OK){

            uri = data?.data!!
            uploadContentViewModel.uploadPdf(uri!!)
            checkForSuccessful()
        }else if (requestCode==VideoRequestCode&&resultCode== Activity.RESULT_OK){

             uri = data?.data!!
             uploadContentViewModel.uploadVideos(uri!!)
             checkForSuccessful()
         }


         else if (requestCode==DocxRequestCode&&resultCode== Activity.RESULT_OK){
            uri = data?.data!!
            uploadContentViewModel.uploadDocx(uri!!)
            checkForSuccessful()
        }else if (requestCode==picRequestCode&&resultCode== Activity.RESULT_OK) {
            uri = data?.data!!
            uploadContentViewModel.uploadPicture(uri!!)
            checkForSuccessful()

        }else if(requestCode==AudioRequestCode&&resultCode== Activity.RESULT_OK){
            uri = data?.data!!
            checkForSuccessful()
            uploadContentViewModel.uploadAudio(uri!!)

        }else{

            Toast.makeText(context, "Nothing was selected", Toast.LENGTH_SHORT).show()
        }
    }

    fun checkForSuccessful(){
        uploadContentViewModel.userLiveDataSuccessful.observe(viewLifecycleOwner,{

            Toast.makeText(context, "Successful uploaded", Toast.LENGTH_SHORT).show()

        })



    }
    fun checkForError(){

        uploadContentViewModel.userLiveDataError.observe(viewLifecycleOwner,{

            Toast.makeText(context, "Failed to upload image", Toast.LENGTH_SHORT).show()

        })
    }


}