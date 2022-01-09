package com.example.covideu.view.Selection.Vaccine

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.covideu.R
import com.example.covideu.databinding.FragmentShowFDAApprovedVaccineBinding
import com.example.covideu.model.VaccineAndTreatments.Vaccines.getFDA_ApprovedVaccines
import com.example.covideu.view.ViewModels.t_v_ViewModel.vaccine.fdaApprovedVaccineViewModel
import com.example.covideu.view.adapter.vaccine.FDA_ApprovedVaccinesRecyclerView


class showFDA_approved_vaccineFragment : Fragment() {
    private val covidDViewModel: fdaApprovedVaccineViewModel by activityViewModels()
    private lateinit var binding: FragmentShowFDAApprovedVaccineBinding
    private lateinit var FDA_approved_vaccin_Adapter: FDA_ApprovedVaccinesRecyclerView
    private var FDA_approved_vaccine_DataList = listOf<getFDA_ApprovedVaccines>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentShowFDAApprovedVaccineBinding.inflate(inflater,container,false)
        return binding.root

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        FDA_approved_vaccin_Adapter = FDA_ApprovedVaccinesRecyclerView(covidDViewModel)

        binding.allFDAApprovedVaccineRecyclerView.adapter =FDA_approved_vaccin_Adapter

        observeFDA_approved()
        covidDViewModel.callFdaAllVaccinesTreatment()

    }


    @SuppressLint("NotifyDataSetChanged")
    fun observeFDA_approved(){
        covidDViewModel.covid19FDAApprovedVaccineLiveData.observe(viewLifecycleOwner,{
            it?.let {
                binding.fdaApprovedVaccineProgressBar .visibility = View.VISIBLE

                FDA_approved_vaccin_Adapter.submitList(it)
                FDA_approved_vaccine_DataList = it

                binding.fdaApprovedVaccineProgressBar.visibility = View.GONE

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
                FDA_approved_vaccin_Adapter.submitList(FDA_approved_vaccine_DataList.filter {
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
                FDA_approved_vaccin_Adapter.submitList(FDA_approved_vaccine_DataList)
                return true
            }


        })


    }

}