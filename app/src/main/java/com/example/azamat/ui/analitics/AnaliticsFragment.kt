package com.example.azamat.ui.analitics

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.azamat.R

class AnaliticsFragment : Fragment() {
	
	companion object {
		fun newInstance() = AnaliticsFragment()
	}
	
	private lateinit var viewModel: AnaliticsViewModel
	
	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		return inflater.inflate(R.layout.analitics_fragment, container, false)
	}
	
	override fun onActivityCreated(savedInstanceState: Bundle?) {
		super.onActivityCreated(savedInstanceState)
		viewModel = ViewModelProviders.of(this).get(AnaliticsViewModel::class.java)
		// TODO: Use the ViewModel
	}
	
}
