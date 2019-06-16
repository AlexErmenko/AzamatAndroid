package com.example.azamat.db.repository

import androidx.lifecycle.LiveData
import com.example.azamat.db.dao.DeviceInfoDao
import com.example.azamat.db.entity.DeviceInfo

class DeviceInfoRepository private constructor(private val infoDao: DeviceInfoDao) {
	fun getInfo(): LiveData<List<DeviceInfo>> = infoDao.getInfo()
}



