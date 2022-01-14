package com.example.covideu.view.Selection.Vaccine

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.covideu.R
import com.example.covideu.databinding.FragmentPhaseFourBinding
import com.example.covideu.model.VaccineAndTreatments.Vaccines.getPhase_four_vaccines
import com.example.covideu.view.ViewModels.treatment_vaccine_ViewModel.vaccine.phaseFourViewModel
import com.example.covideu.view.adapter.vaccine.phaseFourRecyclerView
import java.lang.Exception

class PhaseFourFragment : Fragment() {
    private val covidDViewModel: phaseFourViewModel by activityViewModels()
    private lateinit var binding: FragmentPhaseFourBinding
    private lateinit var phaseFourAdapter: phaseFourRecyclerView
    private var phaseFourDataList = listOf<getPhase_four_vaccines>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPhaseFourBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        phaseFourAdapter = phaseFourRecyclerView(covidDViewModel)

        binding.phaseFourRecyclerView.adapter =phaseFourAdapter

        observePhaseFour()
        covidDViewModel.callPhaseFour()

    }


    @SuppressLint("NotifyDataSetChanged")
    fun observePhaseFour(){

        try {
            covidDViewModel.covid19PhaseFourLiveData .observe(viewLifecycleOwner,{

                it?.let {
                    Log.d("here I am",it.toString())
                    binding.progressBarPhaseFour .visibility = View.VISIBLE
                    phaseFourAdapter.submitList(it)
                    phaseFourDataList = it
                    binding.progressBarPhaseFour .visibility = View.GONE

                }



            })
        }catch (e:Exception){


            checkForError()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()

        requireActivity().menuInflater.inflate(R.menu.custom_menu, menu)
        val searchView = menu.findItem(R.id.searchAction)


        val item_searchView = searchView.actionView as SearchView

        item_searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                phaseFourAdapter.submitList(phaseFourDataList.filter {
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
                phaseFourAdapter.submitList(phaseFourDataList)
                return true
            }


        })


    }

    fun checkForSuccessful(){
        covidDViewModel.covid19PhaseFourLiveDataSuccessful.observe(viewLifecycleOwner,{

            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()

        })

    }
    fun checkForError(){

        covidDViewModel.covid19PhaseFourLiveDataError.observe(viewLifecycleOwner,{

            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()

        })
    }

}