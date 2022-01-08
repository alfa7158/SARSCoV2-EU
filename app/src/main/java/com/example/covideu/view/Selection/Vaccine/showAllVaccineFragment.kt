package com.example.covideu.view.Selection.Vaccine

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.covideu.R
import com.example.covideu.databinding.FragmentShowAllVaccineBinding
import com.example.covideu.databinding.FragmentShowFDAApprovedVaccineBinding
import com.example.covideu.model.VaccineAndTreatments.Vaccines.getAllVaccinesDataItem
import com.example.covideu.model.VaccineAndTreatments.Vaccines.getFDA_ApprovedVaccines
import com.example.covideu.view.ViewModels.t_v_ViewModel.vaccine.AllVaccineViewModel
import com.example.covideu.view.adapter.vaccine.FDA_ApprovedVaccinesRecyclerView
import com.example.covideu.view.adapter.vaccine.getAllVaccineDataRecyclerView


class showAllVaccineFragment : Fragment() {
    private val covidDViewModel: AllVaccineViewModel by activityViewModels()
    private lateinit var binding: FragmentShowAllVaccineBinding
    private lateinit var all_vaccin_Adapter: getAllVaccineDataRecyclerView
    private var AllVaccine_DataList = listOf<getAllVaccinesDataItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentShowAllVaccineBinding.inflate(inflater,container,false)
        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

     all_vaccin_Adapter = getAllVaccineDataRecyclerView(covidDViewModel)

        binding.allVaccineRecyclerView.adapter =all_vaccin_Adapter

        observeFDA_approved_v()
        covidDViewModel.callAllVaccinesTreatment()

    }


    @SuppressLint("NotifyDataSetChanged")
    fun observeFDA_approved_v(){
        covidDViewModel.covidAllVaccinesLiveData.observe(viewLifecycleOwner,{

            it?.let {
                Log.d("here I am",it.toString())
                binding.allVaccineProgressBar.visibility = View.VISIBLE
                all_vaccin_Adapter.submitList(it)
                AllVaccine_DataList = it
                binding.allVaccineProgressBar.visibility = View.GONE

            }


        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        requireActivity().menuInflater.inflate(R.menu.custom_menu, menu)
        val searchView = menu.findItem(R.id.searchAction)


        val item_searchView = searchView.actionView as SearchView

        item_searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                all_vaccin_Adapter.submitList(AllVaccine_DataList.filter {
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
                all_vaccin_Adapter.submitList(AllVaccine_DataList)
                return true
            }


        })


    }


}