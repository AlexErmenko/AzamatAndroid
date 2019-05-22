package com.example.azamat

import com.beust.klaxon.Json
import java.util.*

data class DeviceInfo(
	val id: Int,
	@Json(name = "device_id")
	val device: Int,
	val illumination: Int,
	val temperature: Int,
	val humidity: Int,
	val smoke: Int,
	@Json(name = "created_at")
	val date: String
)
