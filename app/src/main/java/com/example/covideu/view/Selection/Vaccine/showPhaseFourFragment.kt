package com.example.covideu.view.Selection.Vaccine

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.covideu.R
import com.example.covideu.databinding.FragmentPhaseFourBinding
import com.example.covideu.databinding.FragmentShowPhaseOneBinding
import com.example.covideu.databinding.FragmentShowPhaseThreeBinding
import com.example.covideu.model.VaccineAndTreatments.Vaccines.getPhase_four_vaccines
import com.example.covideu.model.VaccineAndTreatments.Vaccines.getPhase_one_vaccines
import com.example.covideu.view.ViewModels.t_v_ViewModel.vaccine.phaseFourViewModel
import com.example.covideu.view.adapter.vaccine.phaseFourRecyclerView
import com.example.covideu.view.adapter.vaccine.phaseOneRecyclerView
import com.example.covideu.view.adapter.vaccine.phaseThreeRecyclerView

class PhaseFourFragment : Fragment() {
    private val covidDViewModel: phaseFourViewModel by activityViewModels()
    private lateinit var binding: FragmentPhaseFourBinding
    private lateinit var phaseFourAdapter: phaseFourRecyclerView
    private val phaseFourDataList = mutableListOf<getPhase_four_vaccines>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPhaseFourBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        phaseFourAdapter = phaseFourRecyclerView(phaseFourDataList,covidDViewModel)

        binding.phaseFourRecyclerView.adapter =phaseFourAdapter

        observePhaseFour()
        covidDViewModel.callPhaseFour()

    }


    @SuppressLint("NotifyDataSetChanged")
    fun observePhaseFour(){
        covidDViewModel.covid19PhaseFourLiveData .observe(viewLifecycleOwner,{

            it?.let {
                Log.d("here I am",it.toString())

                phaseFourDataList.clear()

                phaseFourDataList.addAll(it)

                phaseFourAdapter.notifyDataSetChanged()
                covidDViewModel.covid19PhaseFourLiveData.postValue(null)
            }



        })
    }

}