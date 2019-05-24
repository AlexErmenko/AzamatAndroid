package com.example.azamat

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.os.Handler
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.azamat.SystemServices.NetworkReceiver
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import org.jetbrains.anko.toast
import org.json.JSONArray
import org.json.JSONObject
import java.net.URL

const val URL_API = "http://api.kgemt.org.ua/sunflower/v1/data"

// TODO: Сделать слайдер при загрузке в 1й раз
// TODO: Поменять логин на сканирование QR кода
// TODO: Дописать реализацю построения графика в реальном времени
// TODO: Сделать загрузочный экран при старте приложения
// TODO: Доделать работу с API


class MainActivity : AppCompatActivity() {
	
	private lateinit var mHandler: Handler
	private lateinit var receiver: NetworkReceiver
	
	private val scope = CoroutineScope(newSingleThreadContext("Background"))
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		
//		var filter = IntentFilter(CONNECTIVITY_ACTION)
//		receiver = NetworkReceiver()
//		this.registerReceiver(receiver, filter)
		
//		mHandler = Handler()
		
		
		listView.setOnItemClickListener { parent, view, position, id ->
			toast("Hello $position")
		}
		
		scope.launch {
			val list = loadList()
			withContext(Dispatchers.Main) {
				listView.adapter =
					ArrayAdapter(this@MainActivity, android.R.layout.simple_list_item_1, list)
			}
		}
		
		
		
		swipeLayout.setOnRefreshListener {
			updateConnected()
			if (refreshDisplay) {
				scope.launch{
					
					val list = loadList()
					withContext(Dispatchers.Main) {
						listView.adapter =
							ArrayAdapter(this@MainActivity, android.R.layout.simple_list_item_1, list)
					}
				}
			}
			swipeLayout.isRefreshing = false
		}
	}
	
	
	//Получаю текущее состояние интернет соеденения
	private fun updateConnected() {
		val manager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
		val info: NetworkInfo? = manager.activeNetworkInfo
		when (info?.isConnected) {
			true  -> {
				wifiConnection = true
				mobileConnected = true
			}
			false -> {
				wifiConnection = false
				mobileConnected = false
			}
		}
	}
	
	// Парсю JSON и формирую список для Adapter
	private suspend fun loadList(): MutableList<String> = withContext(Dispatchers.IO) {
		val body = parceJson(URL_API)
		convertJsonToList(body)
	}
	
	//Итерируюсь по JSONArray и формирую List из значений обьектов
	private suspend fun convertJsonToList(
		body: String
	): MutableList<String> = withContext(Dispatchers.IO) {
		
		val jsonArr = JSONArray(body)
		val last = jsonArr[jsonArr.length() - 1] as JSONObject
		
		val list = mutableListOf<String>().apply {
			val id = last["id"] as Int
			var h = last["humidity"] as Int
			val t = last["temperature"] as Int
			val il = last["illumination"] as Int
//			val sm = last["smoke"] as Int
			with(resources) {
				this@apply.add("${getString(R.string.illumination)} $il lux")
				this@apply.add("${getString(R.string.temperature)} $t C°")
				this@apply.add("${getString(R.string.humidity)} $h  (г/м³)")
				
				//TODO: Реализовать датчик качества воздуха!
//				this@apply.add("${getString(R.string.smoke)} $sm (?)")
			}
		}
		list
	}
	
	//Парсисю JSON
	private suspend fun parceJson(urlApi: String): String = withContext(Dispatchers.IO) {
		URL(urlApi).readText()
	}
	
	// Добавление iterator для JSONArray
	operator fun JSONArray.iterator(): Iterator<JSONObject> =
		(0 until length()).asSequence().map {
			get(it) as JSONObject
		}.iterator()
	
	//Состояния подключения к интернету
	companion object {
		private var wifiConnection = false
		private var mobileConnected = false
		val refreshDisplay = true
	}
}
