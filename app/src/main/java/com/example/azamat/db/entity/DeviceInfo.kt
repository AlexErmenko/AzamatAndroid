package com.example.azamat.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.beust.klaxon.Json


/**
 *
 * @property deviceId Int
 * @property id Int
 * @property illumination Int
 * @property temperature Int
 * @property humidity Int
 * @property smoke Int
 * @property date String
 * @constructor
 */
@Entity
data class DeviceInfo(
	@ColumnInfo(name = "ID")
	@Json("id")
	val id: Int,
	
	@PrimaryKey
	@ColumnInfo(name = "DeviceID")
	@Json(name = "device_id")
	val deviceId: Int,
	
	
	@ColumnInfo(name = "Ilum")
	@Json(name = "illumination")
	val illumination: Int,
	
	@ColumnInfo(name = "Temp")
	@Json("temperature")
	val temperature: Int,
	
	
	@Json("humidity")
	@ColumnInfo()
	val humidity: Int,
	
	@Json("smoke")
	@ColumnInfo()
	val smoke: Int,
	
	@Json("bar")
	@ColumnInfo(name = "barometr")
	val barometr: Double,
	
	@Json("gas")
	@ColumnInfo(name = "gas")
	val gas: Int,
	
	
	@Json("air_quality")
	@ColumnInfo(name = "AirQual")
	val airQual: Int,
	
	@Json(name = "created_at")
	@ColumnInfo()
	val date: String
)
