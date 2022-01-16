package com.example.covideu.view.Selection.mainSelect

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.covideu.R
import com.example.covideu.databinding.FragmentMainSelectBinding
import com.example.covideu.databinding.FragmentShowAllVaccineNewsBinding
import com.example.covideu.databinding.FragmentShowCovidNewsBinding
import com.example.covideu.view.identity.SHARED_PREF_FILE
import com.google.firebase.auth.FirebaseAuth

private const val TAG = "mainSelectFragment"
class mainSelectFragment : Fragment() {
    private lateinit var binding: FragmentMainSelectBinding
    private lateinit var user: FirebaseAuth
    private lateinit var sharedPref:SharedPreferences
    private lateinit var sharedPreferencesEditor:SharedPreferences.Editor

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        (activity as AppCompatActivity?)!!.supportActionBar!!.show()

        binding = FragmentMainSelectBinding.inflate(inflater,container,false)
        return binding.root



    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


         sharedPref = requireActivity().getSharedPreferences(SHARED_PREF_FILE, Context.MODE_PRIVATE)
         sharedPreferencesEditor =  sharedPref.edit()
        binding.mainSelectNews.setOnClickListener {

            findNavController().navigate(R.id.action_mainSelectFragment_to_newsMainSelectFragment)
        }

        binding.mainSelectWorldUpdate.setOnClickListener {
            findNavController().navigate(R.id.action_mainSelectFragment_to_countriesFragment2)
        }

        binding.mainSelectVccine.setOnClickListener {

            findNavController().navigate(R.id.action_mainSelectFragment_to_secVaccineAndTreatmentFragment)
        }

        binding.mainSelectCovidEducation.setOnClickListener {
            findNavController().navigate(R.id.action_mainSelectFragment_to_bookOfCovidMainSelectFragment)

        }
        binding.setting.setOnClickListener {

            findNavController().navigate(R.id.action_mainSelectFragment_to_settingFragment)
        }

        binding.mainSelectCheckYouHaveCovid.setOnClickListener {

            findNavController().navigate(R.id.action_mainSelectFragment_to_check_for_covid_Fragment)
        }
        binding.ReportCovidCaseMainSelect.setOnClickListener {

            findNavController().navigate(R.id.action_mainSelectFragment_to_reportCovidCasesFragment)
        }



//
//        binding.logout.setOnClickListener{
//
//
//            findNavController().navigate(R.id.action_mainSelectFragment_to_updateProfileFragment2)
//
//        }




    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        val searchItem = menu.findItem(R.id.searchAction)

        searchItem.isVisible = false
    }





}


