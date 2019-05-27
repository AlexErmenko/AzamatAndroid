package com.example.azamat

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.azamat.SystemServices.NetworkReceiver
import com.example.azamat.UI.Activity.MainActivity
import org.jetbrains.anko.intentFor


const val PreferencesName = "Azamat"

// TODO: Сделать загрузочный экран при старте приложения
class PreloaderActivity : AppCompatActivity() {
	
	lateinit var receiver: NetworkReceiver
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_preloadr)
		val sharedPreferences = getSharedPreferences(PreferencesName, Context.MODE_PRIVATE)
		
		if (sharedPreferences.getBoolean("FirstStart", false)) intentFor<ExampleMainActivity>()
		else intentFor<MainActivity>()
		
		/*val filter =IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
		receiver = NetworkReceiver()
		this.registerReceiver(receiver, filter)*/
		/*GlobalScope.launch(Dispatchers.IO, UNDISPATCHED) {
			delay(1000)
			val hasInternet = isOnline()
			hasInternet.let {
				withContext(Dispatchers.Main) {
					if (hasInternet) startActivity(
						Intent(
							this@PreloaderActivity,
							ExampleMainActivity::class.java
						)
					)
					else Toast.makeText(
						this@PreloaderActivity,
						"Відсутнє інтернет з'єднання!",
						Toast.LENGTH_LONG
					).show()
				}
				
			}
		}*/
	}
	private fun isOnline(): Boolean {
		val connMgr = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
		val networkInfo: NetworkInfo? = connMgr.activeNetworkInfo
		return networkInfo?.isConnected ?: false
	}
	
	private fun updateConnectedFlag(){
		val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
		val activeInfo = connectivityManager.activeNetworkInfo
		if (activeInfo?.isConnected == true){
			wifi = activeInfo.type == ConnectivityManager.TYPE_WIFI
			mobile = activeInfo.type == ConnectivityManager.TYPE_MOBILE
		}else{
			wifi = false
			mobile = false
			
		}
	}
	
	
	override fun onDestroy() {
		super.onDestroy()
		this.unregisterReceiver(receiver)
	}
	
	
	
	companion object{
		private var wifi = false
		private var mobile = false
		var refreshDisplay = true
	}
}
