package com.example.covideu.view.Selection.treatment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.activityViewModels
import com.example.covideu.R
import com.example.covideu.databinding.FragmentClinicalTreatmentBinding
import com.example.covideu.databinding.FragmentFDAApprovedTreatmentBinding
import com.example.covideu.model.VaccineAndTreatments.Treatment.getClinicalTreatments
import com.example.covideu.model.VaccineAndTreatments.Treatment.getFDA_Approvedtreatments
import com.example.covideu.view.ViewModels.t_v_ViewModel.treatment.allClinicalViewModel
import com.example.covideu.view.ViewModels.t_v_ViewModel.treatment.allFdaApprovedTreatment
import com.example.covideu.view.adapter.treatment.allTreatmentRecyclerView
import com.example.covideu.view.adapter.treatment.getFDA_approvedTreatment_RecyclerView
import com.example.covideu.view.adapter.treatment.showAllClinicalTreatmentRecyclerView


class FDA_approved_treatmentFragment : Fragment() {
    private val covidDViewModel: allFdaApprovedTreatment by activityViewModels()
    private lateinit var binding: FragmentFDAApprovedTreatmentBinding
    private lateinit var fdaApproved_treatment_Adapter: getFDA_approvedTreatment_RecyclerView
    private var fdaApproved_treatment_DataList = listOf<getFDA_Approvedtreatments>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity?)!!.supportActionBar!!.show()

        // Inflate the layout for this fragment
        binding = FragmentFDAApprovedTreatmentBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fdaApproved_treatment_Adapter = getFDA_approvedTreatment_RecyclerView(fdaApproved_treatment_DataList,covidDViewModel)

        binding.approvedTreatmentfdaRecyclerView.adapter =fdaApproved_treatment_Adapter

        observeAllTreatment()
        covidDViewModel.callApprovedTreatmentsLiveData()

    }


    @SuppressLint("NotifyDataSetChanged")
    fun observeAllTreatment(){
        covidDViewModel.covid19ApprovedTreatmentsLiveData .observe(viewLifecycleOwner,{
            it?.let {
                binding.fdaApprovedTreatmentProgressBar.visibility = View.VISIBLE

                Log.d("here I am",it.toString())
                fdaApproved_treatment_Adapter.submitList(it)
                fdaApproved_treatment_DataList = it
                binding.fdaApprovedTreatmentProgressBar.visibility = View.GONE

            }



        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()

        requireActivity().menuInflater.inflate(R.menu.custom_menu, menu)
        val searchView = menu.findItem(R.id.searchAction)


        val item_searchView = searchView.actionView as SearchView

        item_searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                fdaApproved_treatment_Adapter.submitList(fdaApproved_treatment_DataList.filter {
                    it.trimedName.lowercase().contains(query!!.lowercase())||
                            it.trimedCategory.lowercase().contains(query!!.lowercase())
                })
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                return true
            }


        })
        searchView.setOnActionExpandListener(object : MenuItem.OnActionExpandListener{
            override fun onMenuItemActionExpand(item: MenuItem?): Boolean {
                return true
            }

            override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
                fdaApproved_treatment_Adapter.submitList(fdaApproved_treatment_DataList)
                return true
            }


        })


    }


}