package com.example.covideu.view.Covid19Check

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.covideu.R
import com.example.covideu.databinding.FragmentCheckForCovidBinding
import com.example.covideu.databinding.FragmentFetchVideosBinding
import com.example.covideu.databinding.FragmentMainSelectBinding


class Check_for_covid_Fragment : Fragment() {
    lateinit var binding: FragmentCheckForCovidBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCheckForCovidBinding.inflate(inflater,container,false)
        return binding.root
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.submitButton.setOnClickListener {

            val checkedRadioButtonId1: Int = binding.questionOneRadioGroup.checkedRadioButtonId
            val checkedRadioButtonId2: Int = binding.questionTwoRadioGroup.checkedRadioButtonId
            val checkedRadioButtonId3: Int = binding.questionThreeRadioGroup.checkedRadioButtonId
            val checkedRadioButtonId4: Int = binding.questionFourRadioGroup.checkedRadioButtonId
            val checkedRadioButtonId5: Int = binding.questionFiveRadioGroup.checkedRadioButtonId
            val checkedRadioButtonId6: Int = binding.questionSixRadioGroup.checkedRadioButtonId
            val checkedRadioButtonId7: Int = binding.questionSevenRadioGroup.checkedRadioButtonId
            val checkedRadioButtonId8: Int = binding.questionEightRadioGroup.checkedRadioButtonId
            val checkedRadioButtonId9: Int = binding.questionNineRadioGroup.checkedRadioButtonId
            val checkedRadioButtonId10: Int = binding.questionTenRadioGroup.checkedRadioButtonId
            val checkedRadioButtonId11: Int = binding.questionElevenRadioGroup.checkedRadioButtonId
            val checkedRadioButtonId12: Int = binding.questionTwelfthRadioGroup.checkedRadioButtonId
            val checkedRadioButtonId13: Int = binding.questionThirteenRadioGroup.checkedRadioButtonId
            val checkedRadioButtonId14: Int = binding.questionFourteenRadioGroup.checkedRadioButtonId
            val checkedRadioButtonId15: Int = binding.questionFifteenRadioGroup.checkedRadioButtonId
            val checkedRadioButtonId16: Int = binding.questionSixteenRadioGroup.checkedRadioButtonId
//            if (checkedRadioButtonId == -1) {
//                // No item selected
//            }
            if(checkedRadioButtonId1==-1&&
                checkedRadioButtonId2==-1&&
                checkedRadioButtonId3==-1&&
                checkedRadioButtonId4==-1&&
                checkedRadioButtonId5==-1&&
                checkedRadioButtonId6==-1&&
                checkedRadioButtonId7==-1&&
                checkedRadioButtonId8==-1&&
                checkedRadioButtonId9==-1&&
                checkedRadioButtonId10==-1&&
                checkedRadioButtonId11==-1&&
                checkedRadioButtonId12==-1&&
                checkedRadioButtonId13==-1&&
                checkedRadioButtonId14==-1&&
                checkedRadioButtonId15==-1&&
                checkedRadioButtonId16==-1

            ){
                Toast.makeText(context, "please complete atl least one questions", Toast.LENGTH_SHORT).show()


            }else{
                if((binding.radioButtonYesQ2.isChecked||
                            binding.radioButtonYesQ3.isChecked||
                            binding.radioButtonYesQ6.isChecked||
                            binding.radioButtonYesQ7.isChecked||
                            binding.radioButtonYesQ8.isChecked) &&
                    binding.radioButtonNoQ1.isChecked&&
                    binding.radioButtonNoQ4.isChecked&&
                    binding.radioButtonNoQ5.isChecked&&
                    binding.radioButtonNoQ9.isChecked&&
                    binding.radioButtonNoQ10.isChecked&&
                    binding.radioButtonNoQ11.isChecked&&
                    binding.radioButtonNoQ12.isChecked&&
                    binding.radioButtonNoQ13.isChecked&&
                    binding.radioButtonNoQ14.isChecked&&
                    binding.radioButtonNoQ15.isChecked
                ){
                    Toast.makeText(context, "please stay at home for 14 days  and seek medical attention if needed", Toast.LENGTH_LONG).show()

                }else if(binding.radioButtonNoQ1.isChecked&& binding.radioButtonNoQ2.isChecked&&
                    binding.radioButtonNoQ3.isChecked&& binding.radioButtonNoQ4.isChecked&&
                    binding.radioButtonNoQ5.isChecked&&
                    binding.radioButtonNoQ6.isChecked&&
                    binding.radioButtonNoQ7.isChecked&&
                    binding.radioButtonNoQ8.isChecked&&
                    binding.radioButtonNoQ9.isChecked&&
                    binding.radioButtonNoQ10.isChecked&&
                    binding.radioButtonNoQ11.isChecked&&
                    binding.radioButtonNoQ12.isChecked&&
                    binding.radioButtonNoQ13.isChecked&&
                    binding.radioButtonNoQ14.isChecked&&
                    binding.radioButtonNoQ15.isChecked){

                    Toast.makeText(context, "The the result of survey shows that your symptoms are not covid related  ", Toast.LENGTH_LONG).show()
                }else if(binding.radioButtonNoQ1.isChecked&& binding.radioButtonNoQ2.isChecked&&
                    binding.radioButtonNoQ3.isChecked&& binding.radioButtonNoQ4.isChecked&&
                    binding.radioButtonNoQ5.isChecked&&
                    binding.radioButtonNoQ6.isChecked&&
                    binding.radioButtonNoQ7.isChecked&&
                    binding.radioButtonNoQ8.isChecked&&
                    binding.radioButtonYesQ9.isChecked&&
                    binding.radioButtonYesQ10.isChecked&&
                    binding.radioButtonYesQ11.isChecked&&
                    binding.radioButtonYesQ12.isChecked&&
                    binding.radioButtonYesQ13.isChecked&&
                    binding.radioButtonYesQ14.isChecked&&
                    binding.radioButtonYesQ15.isChecked&&
                    binding.radioButtonYesQ16.isChecked
                ){
                    Toast.makeText(context, "The the result of survey shows that your symptoms are covid related, please stay home and seek medical attention if needed", Toast.LENGTH_LONG).show()


                }else if((binding.radioButtonYesQ4.isChecked||binding.radioButtonYesQ5.isChecked)&&binding.radioButtonNoQ1.isChecked&& binding.radioButtonNoQ2.isChecked&&
                    binding.radioButtonNoQ3.isChecked&& binding.radioButtonNoQ6.isChecked&&
                    binding.radioButtonNoQ7.isChecked&&
                    binding.radioButtonNoQ8.isChecked&&
                    binding.radioButtonNoQ9.isChecked&&
                    binding.radioButtonNoQ10.isChecked&&
                    binding.radioButtonNoQ11.isChecked&&
                    binding.radioButtonNoQ12.isChecked&&
                    binding.radioButtonNoQ13.isChecked&&
                    binding.radioButtonNoQ14.isChecked&&
                    binding.radioButtonNoQ15.isChecked&&
                    binding.radioButtonNoQ16.isChecked){

                    Toast.makeText(context, "Please stay home until result for covid-19 is recived", Toast.LENGTH_LONG).show()


                }


                else{

                    Toast.makeText(context, "Your symptoms are covid related. ", Toast.LENGTH_SHORT).show()
                }



            }




        }



    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        val searchItem = menu.findItem(R.id.searchAction)

        searchItem.isVisible = false
    }


}