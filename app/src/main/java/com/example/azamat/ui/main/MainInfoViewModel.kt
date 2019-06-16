package com.example.azamat.ui.main

import androidx.lifecycle.ViewModel
import com.example.azamat.db.repository.DeviceInfoRepository

class MainInfoViewModel internal constructor(
	repository: DeviceInfoRepository
) : ViewModel() {
	
	val data = repository.getInfo()
	
}
