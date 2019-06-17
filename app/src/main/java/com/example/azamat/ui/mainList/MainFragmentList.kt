package com.example.azamat.ui.mainList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.azamat.R
import com.example.azamat.db.AppDatabase

class MainFragmentList : Fragment() {
	
	companion object {
		fun newInstance() = MainFragmentList()
	}
	
	private lateinit var viewModel: MainFragmentListViewModel
	lateinit var db: AppDatabase
	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		return inflater.inflate(R.layout.fragment_main_list, container, false)
	}
	
	override fun onActivityCreated(savedInstanceState: Bundle?) {
		super.onActivityCreated(savedInstanceState)
		
		
		
		
		viewModel = ViewModelProviders.of(this).get(MainFragmentListViewModel::class.java)
		// TODO: Use the ViewModel
	}
	
}
