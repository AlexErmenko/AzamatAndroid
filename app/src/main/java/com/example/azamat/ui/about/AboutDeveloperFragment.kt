package com.example.azamat.ui.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.azamat.R

class AboutDeveloperFragment : Fragment() {
	
	companion object {
		fun newInstance() = AboutDeveloperFragment()
	}
	
	private lateinit var viewModel: AboutDeveloperViewModel
	
	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		return inflater.inflate(R.layout.about_developer_fragment, container, false)
	}
	
	override fun onActivityCreated(savedInstanceState: Bundle?) {
		super.onActivityCreated(savedInstanceState)
		viewModel = ViewModelProviders.of(this).get(AboutDeveloperViewModel::class.java)
		// TODO: Use the ViewModel
	}
	
}
