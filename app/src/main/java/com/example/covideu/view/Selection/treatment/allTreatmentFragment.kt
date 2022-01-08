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
import com.example.covideu.databinding.FragmentAllTreatmentBinding
import com.example.covideu.databinding.FragmentShowFDAApprovedVaccineBinding
import com.example.covideu.model.VaccineAndTreatments.Treatment.getAllTreatmentsData
import com.example.covideu.view.ViewModels.t_v_ViewModel.treatment.allTreatmentViewModel
import com.example.covideu.view.adapter.newsRecyclers.allVaccineNewsRecyclerView
import com.example.covideu.view.adapter.treatment.allTreatmentRecyclerView
import com.example.covideu.view.adapter.vaccine.phaseFourRecyclerView


class allTreatmentFragment : Fragment() {
    private val covidDViewModel: allTreatmentViewModel by activityViewModels()
    private lateinit var binding: FragmentAllTreatmentBinding
    private lateinit var all_treatment_Adapter: allTreatmentRecyclerView
    private var all_treatment_DataList = listOf<getAllTreatmentsData>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity?)!!.supportActionBar!!.show()

        binding = FragmentAllTreatmentBinding.inflate(inflater,container,false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        all_treatment_Adapter = allTreatmentRecyclerView(covidDViewModel)

        binding.allTreatmentRecyclerView.adapter =all_treatment_Adapter

        observeAllTreatment()
        covidDViewModel.callAllCovidTreatment()

    }


    @SuppressLint("NotifyDataSetChanged")
    fun observeAllTreatment(){
        covidDViewModel.covidAllTreatmentsLiveData .observe(viewLifecycleOwner,{

            it?.let {
                Log.d("here I am",it.toString())
                binding.allTreatmentProgressBar.visibility = View.VISIBLE
                all_treatment_Adapter.submitList(it)
                all_treatment_DataList = it
                binding.allTreatmentProgressBar.visibility = View.GONE

            }



        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        requireActivity().menuInflater.inflate(R.menu.custom_menu, menu)
        val searchView = menu.findItem(R.id.searchAction)


        val item_searchView = searchView.actionView as SearchView

        item_searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                all_treatment_Adapter.submitList(all_treatment_DataList.filter {
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
                all_treatment_Adapter.submitList(all_treatment_DataList)
                return true
            }


        })


    }








}