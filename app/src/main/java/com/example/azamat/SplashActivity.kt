package com.example.azamat

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.*

class SplashActivity : AppCompatActivity() {
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_splash)
		
		GlobalScope.launch(Dispatchers.IO, CoroutineStart.UNDISPATCHED) {
			delay(2000)
			val hasInternet = checkInternet()
			hasInternet?.let {
				withContext(Dispatchers.Main) {
					if (hasInternet) startActivity(Intent(this@SplashActivity, MainActivity::class.java))
					else Toast.makeText(this@SplashActivity, "Відсутнє інтернет з'єднання!", Toast.LENGTH_LONG).show()
				}
				
			}
		}
	}
	
	
	private fun checkInternet(): Boolean? {
		val manager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
		val info: NetworkInfo? = manager.activeNetworkInfo
		return info?.isConnected
	}
}
