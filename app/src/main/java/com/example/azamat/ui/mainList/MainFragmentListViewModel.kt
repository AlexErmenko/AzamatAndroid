package com.example.azamat.ui.mainList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.azamat.db.entity.DeviceInfo
import com.example.azamat.db.repository.DeviceInfoRepository
import com.example.azamat.model.ListItem

class MainFragmentListViewModel(val repository: DeviceInfoRepository) : ViewModel() {
	
	
	private val _list: MutableLiveData<List<ListItem>>
	
	val listItem: MutableLiveData<DeviceInfo>
	
	init {
		_list = MutableLiveData()
		listItem = MutableLiveData(repository.getInfo())
//		val res = repository.getInfo()
//		listItem
	}
	
	
	val list: LiveData<List<ListItem>>
		get() = _list
	
	
	fun loadData() {
		listItem.postValue(repository.getInfo())
	}
	
}
