package com.example.covideu.view.Selection.setting

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.NavController
import com.example.covideu.R
import com.example.covideu.view.Selection.setting.Translate.BaseActivity

/**
 * this class is to help in changing the language options
 */

class SettingFragment : Fragment() {
    private lateinit var navController: NavController


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_setting, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val languageOption:Button = view.findViewById(R.id.traslate_option_button)
            languageOption.setOnClickListener {
            val intent = Intent(this@SettingFragment.requireContext(), BaseActivity::class.java)
            startActivity(intent)

        }

    }


}