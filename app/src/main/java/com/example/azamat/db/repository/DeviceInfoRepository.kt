package com.example.azamat.db.repository

import androidx.lifecycle.LiveData
import com.beust.klaxon.Klaxon
import com.example.azamat.db.dao.DeviceInfoDao
import com.example.azamat.db.entity.DeviceInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject
import java.net.URL
import java.util.*

class DeviceInfoRepository constructor(private val infoDao: DeviceInfoDao) {
	
	
	private suspend fun parceJson(urlApi: String) =
		withContext(Dispatchers.IO) {
			URL(urlApi).readText()
		}
	
	
	operator fun JSONArray.iterator(): Iterator<JSONObject> =
		(0 until length()).asSequence().map {
			get(it) as JSONObject
		}.iterator()
	
	
	private fun iterateJsonArray(
		str: String,
		osX: String?,
		osY: String?
	): SortedMap<Double, Double> {
		
		val jsonArr = JSONArray(str)
		
		return mutableMapOf<Double, Double>().apply {
			for (i in jsonArr) {
				val x = (i[osX] as Int).toDouble()
				val y = (i[osY] as Int).toDouble()
				this[x] = y
			}
		}.toSortedMap()
	}
	
	fun getInfo(): LiveData<List<DeviceInfo>> = infoDao.getInfo()
	
	
	fun readInfo(str: String): DeviceInfo? = Klaxon().parse<DeviceInfo>(str)
	
	
	suspend fun load(): Int? {
		val res = parceJson("http://api.kgemt.org.ua/amat/v2/data")
		val obj = readInfo(res)?.id
		
		
		return obj
	}
	
}



