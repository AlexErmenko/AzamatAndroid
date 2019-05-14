package com.example.azamat

import android.content.BroadcastReceiver
import android.content.Context
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.ConnectivityManager.CONNECTIVITY_ACTION
import android.net.NetworkInfo
import android.os.Bundle
import android.os.Handler
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import org.jetbrains.anko.longToast
import org.json.JSONArray
import org.json.JSONObject
import java.net.URL
import java.util.*

const val URL_API = "http://api.kgemt.org.ua/sunflower/v1/data"


class MainActivity : AppCompatActivity() {
	private lateinit var mRandom: Random
	private lateinit var mHandler: Handler
	private lateinit var mRunnable: Runnable
	
	
	private lateinit var receiver: BroadcastReceiver
	
	//Todo: Добавить широковещательное уведомление о появление интернета
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		
		var filter = IntentFilter(CONNECTIVITY_ACTION)
		mRandom = Random()
		mHandler = Handler()
		listView.setOnItemClickListener { parent, view, position, id ->
			longToast("Hello from ${id} and $position")
		}
	}
	
	
	private fun checkInternet(): Boolean? {
		val manager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
		val info: NetworkInfo? = manager.activeNetworkInfo
		return info?.isConnected
	}
	
	override fun onStart() {
		super.onStart()
		val internet = checkInternet()
		
		swipeLayout.setOnRefreshListener {
			internet?.let {
				if (internet) {
					mRunnable = Runnable {
						GlobalScope.launch(Dispatchers.IO, CoroutineStart.UNDISPATCHED) {
							val answer = parceJson(URL_API)
							val list = iterateJsonArray(answer)
							withContext(Dispatchers.Main) {
								listView.adapter = ArrayAdapter(
									this@MainActivity,
									android.R.layout.simple_list_item_1,
									list
								)
							}
						}
						swipeLayout.isRefreshing = false
					}
					mHandler.postDelayed(
						mRunnable,
						(randomInRange(1, 4) * 1000).toLong() // Delay 1 to 5 seconds
					)
				}
			}
		}
		
	}
	
	private fun iterateJsonArray(
		str: String
	): MutableList<Int> {
		
		val jsonArr = JSONArray(str)
		
		return mutableListOf<Int>().apply {
			for (i in jsonArr) {
				val id = i["id"] as Int
				this.add(id)
			}
		}
	}
	
	private suspend fun parceJson(urlApi: String) =
		withContext(Dispatchers.Default) {
			URL(urlApi).readText()
		}
	
	operator fun JSONArray.iterator(): Iterator<JSONObject> =
		(0 until length()).asSequence().map {
			get(it) as JSONObject
		}.iterator()
	
	
	companion object{
		const val WIFI = "Wi-Fi"
		const val ANY = "Any"
		
		
		private var wifiConnection = false
		private var mobileConnected = false
		var refreshDisplay = true
		
	}
	
	// Custom method to get a random number from the provided range
	private fun randomInRange(min: Int, max: Int): Int {
		// Define a new Random class
		val r = Random()
		
		// Get the next random number within range
		// Including both minimum and maximum number
		return r.nextInt((max - min) + 1) + min;
	}
}
