package com.example.azamat

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.example.azamat.db.AppDatabase
import com.example.azamat.db.entity.InfoEntity

class DataRepository private constructor(private val db: AppDatabase) {
	private val ObservableInfo: MediatorLiveData<List<InfoEntity>>
	
	val infos: LiveData<List<InfoEntity>>
		get() = ObservableInfo
	
	init {
		ObservableInfo = MediatorLiveData()
		ObservableInfo.addSource(
			db.infoDao().loadAllInfo()
		) { it: List<InfoEntity>? ->
			if (db.databaseCreated.value != null)
				ObservableInfo.postValue(it)
		}
	}
	
}

