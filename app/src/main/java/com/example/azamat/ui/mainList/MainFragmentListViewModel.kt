package com.example.azamat.ui.mainList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainFragmentListViewModel : ViewModel() {
	
	
	private val _list: MutableLiveData<List<ListItem>>
	
	init {
		_list = MutableLiveData()
	}
	
	
	val list: LiveData<List<ListItem>>
		get() = _list
}
