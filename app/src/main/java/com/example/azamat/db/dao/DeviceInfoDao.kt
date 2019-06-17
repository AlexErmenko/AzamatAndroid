package com.example.azamat.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.azamat.db.entity.DeviceInfo


@Dao
interface DeviceInfoDao {
	
	@Query("SELECT * FROM DeviceInfo")
	fun getInfo(): DeviceInfo
	
	@Query("DELETE FROM DeviceInfo")
	fun removeAll()
	
	
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	fun appendInfo(deviceInfo: DeviceInfo)
	
	
}