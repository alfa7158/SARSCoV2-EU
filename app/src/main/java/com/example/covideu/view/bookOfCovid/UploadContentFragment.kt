package com.example.covideu.view.bookOfCovid

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import com.example.covideu.R
import com.example.covideu.databinding.FragmentUploadVideosBinding
import com.example.covideu.view.ViewModels.bookOfCovidViewModels.bookOfCoivdViewModel
import com.example.covideu.view.identity.SHARED_PREF_FILE
import com.google.firebase.auth.FirebaseAuth
import java.util.*

/**
 * This class contains the method that are responsible for uploading content or adding it to the
 * firebase
 */
private const val TAG = "UploadVideosFragment"
class UploadContentFragment : Fragment() {

    private val uploadContentViewModel: bookOfCoivdViewModel by activityViewModels()
    private lateinit var  sharedPref:SharedPreferences

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPref = requireActivity().getSharedPreferences(SHARED_PREF_FILE, Context.MODE_PRIVATE)
        var uploadType =  arrayOf("Video","Photos","Audio","Docx","PDF")
        val myAdapter = context?.let { ArrayAdapter<String>(it,android.R.layout.simple_spinner_dropdown_item,uploadType) }


        /**
         * below a spinner created for the user to choose the type of content to upload to the
         * book of covid
         */
        binding.mySpinner.adapter = myAdapter
    //the two functions below is to control the spinner
        binding.mySpinner.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                /**
                 * the if conditions below is to check for the spinner position and hides other
                 * buttons based on what user chooses
                 */
               if(position==0){

                   binding.uploadVideoButton.isVisible = true

                   binding.uploadAPicBookOfCovid.isVisible = false
                   binding.uplaodAudioButton.isVisible = false
                   binding.UploadDocxButton.isVisible = false
                   binding.uploadPdfutton.isVisible = false


               }else if(position==1){

                   binding.uploadAPicBookOfCovid.isVisible = true


                   binding.uploadVideoButton.isVisible = false
                   binding.uplaodAudioButton.isVisible = false
                   binding.UploadDocxButton.isVisible = false
                   binding.uploadPdfutton.isVisible = false
               }else if (position==2){
                   binding.uplaodAudioButton.isVisible = true



                   binding.uploadAPicBookOfCovid.isVisible = false
                   binding.uploadVideoButton.isVisible = false
                   binding.UploadDocxButton.isVisible = false
                   binding.uploadPdfutton.isVisible = false
               }else if (position==3){
                   binding.UploadDocxButton.isVisible = true


                   binding.uplaodAudioButton.isVisible = false
                   binding.uploadAPicBookOfCovid.isVisible = false
                   binding.uploadVideoButton.isVisible = false
                   binding.uploadPdfutton.isVisible = false

               }else if(position==4){
                   binding.uploadPdfutton.isVisible = true
                   binding.UploadDocxButton.isVisible = false
                   binding.uplaodAudioButton.isVisible = false
                   binding.uploadAPicBookOfCovid.isVisible = false
                   binding.uploadVideoButton.isVisible = false

               }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }


        }


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

    /**
     * The function below is to select an image from the user phone
     */
    private fun selectPic() {

        val intent = Intent()

        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT

        startActivityForResult(intent,picRequestCode)


    }




    /**
     * The function below is to select an video from the user phone
     */

    private fun selectVideo() {

        val intent = Intent()

        intent.type = "video/*"
        intent.action = Intent.ACTION_GET_CONTENT

        startActivityForResult(intent,VideoRequestCode)


    }

    /**
     * The function below is to select an pdf from the user phone
     */

    private fun selectPdf() {

        val intent = Intent()

        intent.type = "pdf/*"
        intent.action = Intent.ACTION_GET_CONTENT

        startActivityForResult(intent,PdfRequestCode)


    }
    /**
     * The function below is to select an docx from the user phone
     */

    private fun selectDocx() {

        val intent = Intent()

        intent.type = "docx/*"
        intent.action = Intent.ACTION_GET_CONTENT

        startActivityForResult(intent,DocxRequestCode)


    }
    /**
     * The function below is to select an audio from the user phone
     */
    private fun selectAudio() {

        val intent = Intent()

        intent.type = "audio/*"
        intent.action = Intent.ACTION_GET_CONTENT

        startActivityForResult(intent,AudioRequestCode)


    }

    /**
     * the function below to get the result based of the request code and upload content to the
     * Firestore
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        binding.saveTitleDecripButton.setOnClickListener {

            if (requestCode==PdfRequestCode&&resultCode== Activity.RESULT_OK){
                binding.progressBar2.visibility = View.VISIBLE

                uri = data?.data!!
//            uploadContentViewModel.uploadPdf(uri!!)
                var title = binding.addTitleEditText.text.toString()
                var description = binding.addDescriptionEditText.text.toString()
                uploadContentViewModel.uploadPdfFireStore(uri!!,Math.random().toString(),title,description)

                checkForSuccessful()

            }else if (requestCode==VideoRequestCode&&resultCode== Activity.RESULT_OK){
                binding.progressBar2.visibility = View.VISIBLE

                uri = data?.data!!
                //uploadContentViewModel.uploadVideos(uri!!)
                var title = binding.addTitleEditText.text.toString()
                var description = binding.addDescriptionEditText.text.toString()
                uploadContentViewModel.uploadVideoFireStore(uri!!,Math.random().toString(),title,description)
                checkForSuccessful()


            }


            else if (requestCode==DocxRequestCode&&resultCode== Activity.RESULT_OK){
                binding.progressBar2.visibility = View.VISIBLE

                uri = data?.data!!
//            uploadContentViewModel.uploadDocx(uri!!)
                var title = binding.addTitleEditText.text.toString()
                var description = binding.addDescriptionEditText.text.toString()
                uploadContentViewModel.uploadDocxFireStore(uri!!,Math.random().toString(),title,description)

                checkForSuccessful()


            }else if (requestCode==picRequestCode&&resultCode== Activity.RESULT_OK) {
                binding.progressBar2.visibility = View.VISIBLE

                uri = data?.data!!
//             Log.d(TAG,"user id:${sharedPref.getString("uid","")!!}")
                var date = Date()
//            uploadContentViewModel.uploadPicture(uri!!,sharedPref.getString("uid","")!!)
                var title = binding.addTitleEditText.text.toString()
                var description = binding.addDescriptionEditText.text.toString()
                uploadContentViewModel.uploadPictureFireStore(uri!!,Math.random().toString(),title,description,FirebaseAuth.getInstance().uid.toString())
                //      uploadContentViewModel.uploadPictureStorage(uri!!,sharedPref.getString("uid","")!!)

            }else if(requestCode==AudioRequestCode&&resultCode== Activity.RESULT_OK){
                binding.progressBar2.visibility = View.VISIBLE

                uri = data?.data!!
                checkForSuccessful()
                //uploadContentViewModel.uploadAudio(uri!!)
                var title = binding.addTitleEditText.text.toString()
                var description = binding.addDescriptionEditText.text.toString()
                uploadContentViewModel.uploadAudioFireStore(uri!!,Math.random().toString(),title,description)


            }else{
                binding.progressBar2.visibility = View.INVISIBLE


                Toast.makeText(context, "Nothing was selected", Toast.LENGTH_SHORT).show()
            }

        }

    }

    fun checkForSuccessful(){
        uploadContentViewModel.userLiveDataSuccessful.observe(viewLifecycleOwner,{

            Toast.makeText(context, "Successful uploaded", Toast.LENGTH_SHORT).show()
            binding.progressBar2.visibility = View.INVISIBLE

        })



    }
    fun checkForError(){

        uploadContentViewModel.userLiveDataError.observe(viewLifecycleOwner,{

            Toast.makeText(context, "Failed to upload image", Toast.LENGTH_SHORT).show()

        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        val searchItem = menu.findItem(R.id.searchAction)

        searchItem.isVisible = false
    }


}