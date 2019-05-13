package com.example.azamat

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import org.jetbrains.anko.alert
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
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		listView.setOnItemClickListener { parent, view, position, id ->
			longToast("Hello from ${id} and $position")
		}
	}
	
	
	
	override fun onStart() {
		super.onStart()
		mRandom = Random()
		mHandler = Handler()
		
		swipeLayout.setOnRefreshListener {
			// Initialize a new Runnable
			mRunnable = Runnable {
				GlobalScope.launch(Dispatchers.IO,CoroutineStart.UNDISPATCHED) {
					val answer = parceJson(URL_API)
					var list  = iterateJsonArray(answer)
					withContext(Dispatchers.Main) {
						listView.adapter = ArrayAdapter(this@MainActivity, android.R.layout.simple_list_item_1,list)
					}
				}
				swipeLayout.isRefreshing = false
			}
			mHandler.postDelayed(
				mRunnable,
				(randomInRange(3, 5) * 1000).toLong() // Delay 1 to 5 seconds
			)
		}
		
		
	}
	private fun iterateJsonArray(
		str: String
	):MutableList<Int>{
		
		val jsonArr = JSONArray(str)
		
		return  mutableListOf<Int>().apply{
			for (i in jsonArr){
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
	
	
	
	// Custom method to generate random HSV color
	fun randomHSVColor(): Int {
		// Generate a random hue value between 0 to 360
		val hue = mRandom.nextInt(361)
		// We make the color depth full
		val saturation = 1.0f
		// We make a full bright color
		val value = 1.0f
		// We avoid color transparency
		val alpha = 255
		// Finally, generate the color
		// Return the color
		return Color.HSVToColor(alpha, floatArrayOf(hue.toFloat(), saturation, value))
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
