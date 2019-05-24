package com.example.azamat.UI.Activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceFragmentCompat
import com.example.azamat.R

class SettingsActivity : AppCompatActivity() {
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.settings_activity)
		supportFragmentManager
				.beginTransaction()
				.replace(R.id.settings, SettingsFragment())
				.commit()
		supportActionBar?.setDisplayHomeAsUpEnabled(true)
	}
	
	//Фрагмент с настройками для приложения
	class SettingsFragment : PreferenceFragmentCompat() {
		override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
			setPreferencesFromResource(R.xml.root_preferences, rootKey)
		}
	}
}