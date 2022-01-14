package com.example.covideu.view.Selection.reportCovidCases

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.activityViewModels
import com.example.covideu.R
import com.example.covideu.databinding.FragmentClinicalTreatmentBinding
import com.example.covideu.databinding.FragmentReportCovidCasesBinding
import com.example.covideu.view.ViewModels.newsViewModels.allVaccineNewsViewModel
import com.example.covideu.view.ViewModels.reportCasesViewModel.reportCasesViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.firebase.auth.FirebaseAuth

class ReportCovidCasesFragment : Fragment() {
    lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var binding: FragmentReportCovidCasesBinding

    private val reportCovidViewModel: reportCasesViewModel by activityViewModels()

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






    }

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
        task.addOnSuccessListener {
            if (it != null) {

                reportCovidViewModel.addCommentsFireStore(
                    FirebaseAuth.getInstance().uid.toString(),
                    10235678,
                    it.longitude,
                    it.latitude,
                    2,
                    "Friend"
                )
            }
        }

    }
}