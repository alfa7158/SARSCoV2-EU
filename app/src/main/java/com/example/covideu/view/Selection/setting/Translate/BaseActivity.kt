package com.example.covideu.view.Selection.setting.Translate

import android.os.Bundle
import android.widget.Button
import com.example.covideu.R
import com.zeugmasolutions.localehelper.LocaleAwareCompatActivity
import com.zeugmasolutions.localehelper.Locales

/**
 * This class is responsible for manging languages
 */
class BaseActivity : LocaleAwareCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)

        val englishButton: Button = findViewById(R.id.button_en)
        val arabicButton: Button = findViewById(R.id.button_ar)
        englishButton.setOnClickListener {
            updateLocale(Locales.English)


        }
        arabicButton.setOnClickListener {
            updateLocale(Locales.Arabic)


        }
    }
}