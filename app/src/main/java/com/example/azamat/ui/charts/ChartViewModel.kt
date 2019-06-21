package com.example.azamat.ui.charts

import android.app.Application
import android.app.admin.DeviceAdminInfo
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.azamat.db.AppDatabase
import com.example.azamat.db.dao.DeviceInfoDao
import com.example.azamat.db.entity.DeviceInfo
import com.example.azamat.db.repository.DeviceInfoRepository

class ChartViewModel : ViewModel() {
	
	lateinit var app: Application
	lateinit var db : AppDatabase
	lateinit var dao : DeviceInfoDao
	lateinit var repository: DeviceInfoRepository
	
	
	private var _data: MutableLiveData<DeviceInfo>
	
	val data: LiveData<DeviceInfo>
		get() = _data
	
	init {
		_data =MutableLiveData()
	}
	
	
}
