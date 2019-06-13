package com.example.azamat.ui.help

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.azamat.R

class UserHelpFragment : Fragment() {
	
	companion object {
		fun newInstance() = UserHelpFragment()
	}
	
	private lateinit var viewModel: UserHelpViewModel
	
	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		return inflater.inflate(R.layout.user_help_fragment, container, false)
	}
	
	override fun onActivityCreated(savedInstanceState: Bundle?) {
		super.onActivityCreated(savedInstanceState)
		viewModel = ViewModelProviders.of(this).get(UserHelpViewModel::class.java)
		// TODO: Use the ViewModel
	}
	
}
