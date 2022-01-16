package com.example.covideu.view.Selection.reportCovidCases

import android.content.pm.PackageManager
import android.location.Geocoder
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.covideu.R
import com.example.covideu.databinding.FragmentReportCovidCasesBinding
import com.example.covideu.view.ViewModels.reportCasesViewModel.reportCasesViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.firebase.auth.FirebaseAuth

class ReportCovidCasesFragment : Fragment() {
    lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var binding: FragmentReportCovidCasesBinding
    var theIdentityType = ""
    var theNumberOfCases = 0
    var theRelationship = ""
    private val reportCovidViewModel: reportCasesViewModel by activityViewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentReportCovidCasesBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireActivity())


            binding.saveRport.setOnClickListener {

                getLocationPermission()

            }


        /**
         * below we defined three spinner for covid-19 cases reporting
         */
        var identityType =  arrayOf("National ID","Passport","foreign ID")
        val identityAdapter = context?.let { ArrayAdapter(it,android.R.layout.simple_spinner_dropdown_item,identityType)
        }
        var numberOfCases =  arrayOf(1,2,3,4,5,6,7,8,9,10)
        val numberOfCasesAdapter = context?.let { ArrayAdapter(it,android.R.layout.simple_spinner_dropdown_item,numberOfCases)
        }
        var relationship =  arrayOf("family member","Friend","other","self")
        val relationshipAdapter = context?.let { ArrayAdapter(it,android.R.layout.simple_spinner_dropdown_item,relationship)
        }


        binding.spinnerIdType.adapter = identityAdapter
        binding.spinnerNumberOfCases.adapter = numberOfCasesAdapter
        binding.spinnerRelationship.adapter = relationshipAdapter

        /**
         * below we used onItemSelectedListen to
         */
        binding.spinnerIdType.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                when (position) {
                    0 -> { theIdentityType = identityType[0]}

                    1 -> { theIdentityType = identityType[1] }

                    2 -> { theIdentityType = identityType[2] }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

        binding.spinnerNumberOfCases.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                when (position) {
                    0 -> { theNumberOfCases = numberOfCases[0] }

                    1 -> { theNumberOfCases = numberOfCases[1] }

                    2 -> { theNumberOfCases = numberOfCases[2] }

                    3 -> {theNumberOfCases = numberOfCases[3] }

                    4 -> { theNumberOfCases = numberOfCases[4] }

                    5 -> { theNumberOfCases = numberOfCases[5] }

                    6 -> { theNumberOfCases = numberOfCases[6] }

                    7 -> { theNumberOfCases = numberOfCases[7] }

                    8 -> { theNumberOfCases = numberOfCases[8] }

                    9 -> { theNumberOfCases = numberOfCases[9] }

                    10 -> { theNumberOfCases = numberOfCases[10] }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

        binding.spinnerRelationship.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                when (position) {
                    0 -> { theRelationship = relationship[0] }

                    1 -> { theRelationship = relationship[1]}

                    2 -> { theRelationship = relationship[2] }

                    3 -> { theRelationship = relationship[3] }

                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }



    }

    /**
     * The function below is to get the current location of the phone
     */

    private fun getLocationPermission() {

        val task = fusedLocationProviderClient.lastLocation
        if (ActivityCompat.checkSelfPermission(
                requireActivity(),
                android.Manifest.permission.ACCESS_FINE_LOCATION
            )
            != PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(
                requireActivity(),
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            )
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                101
            )
            return
        }



        task.addOnSuccessListener { location ->
            if (location != null) {






                val identityEditText =  binding.identityValueEditText.text.toString()
                val acknowledgmentCheckBox  = binding.checkBox

                if(identityEditText.isNotEmpty()&&identityEditText.isNotEmpty()&&
                    acknowledgmentCheckBox.isChecked){
                    reportCovidViewModel.addReportFireStore(
                        FirebaseAuth.getInstance().uid.toString(),theIdentityType,
                        identityEditText,
                        location.longitude,
                        location.latitude,
                        theNumberOfCases,
                        theRelationship,
                        getAddress(location.latitude,location.longitude))
                    Toast.makeText(context, "Tank you for reporting", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_reportCovidCasesFragment_to_mainSelectFragment)                }else{

                    Toast.makeText(context, "Please finish all required fields", Toast.LENGTH_SHORT).show()

                }



            }
        }

    }
    //the function below is to convert the longitude and latitude to readable address
    private fun getAddress(lat: Double, lng: Double): String {
        val geocoder = Geocoder(context)
        val list = geocoder.getFromLocation(lat, lng, 1)
        return list[0].getAddressLine(0)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        val searchItem = menu.findItem(R.id.searchAction)

        searchItem.isVisible = false
    }


}