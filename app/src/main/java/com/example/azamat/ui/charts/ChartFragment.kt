package com.example.azamat.ui.charts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.azamat.R
import com.example.azamat.databinding.ChartFragmentBinding

class ChartFragment : Fragment() {
	
	lateinit var bind : ChartFragmentBinding
	private lateinit var chartViewModel: ChartViewModel
	
	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		bind = DataBindingUtil.inflate<ChartFragmentBinding>(
			inflater,
			R.layout.chart_fragment,
			container,
			false
		)
		chartViewModel = ViewModelProviders.of(this).get(ChartViewModel::class.java)
		
		
		return bind.root
	}
	
}
