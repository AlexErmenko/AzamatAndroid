package com.example.azamat

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.example.azamat.db.AppDatabase
import com.example.azamat.db.entity.DeviceInfo

class DataRepository private constructor(private val db: AppDatabase) {
	private val ObservableInfo: MediatorLiveData<List<DeviceInfo>>
	
	val infos: LiveData<List<DeviceInfo>>
		get() = ObservableInfo
	
	init {
		ObservableInfo = MediatorLiveData()
		ObservableInfo.addSource(
			db.infoDao().getInfo()
		) { it: List<DeviceInfo>? ->
			if (db.databaseCreated.value != null)
				ObservableInfo.postValue(it)
		}
	}
	
}

