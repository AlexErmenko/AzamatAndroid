package com.example.azamat.SystemServices

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import androidx.core.content.getSystemService
import com.example.azamat.SplashActivity.Companion.refreshDisplay

class NetworkReceiver : BroadcastReceiver() {
	
	override fun onReceive(context: Context, intent: Intent) {
		// This method is called when the BroadcastReceiver is receiving an Intent broadcast.
		TODO("NetworkReceiver.onReceive() is not implemented")
		
		
		val connection = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
		val networkInfo = connection.activeNetworkInfo
		if (networkInfo.type == ConnectivityManager.TYPE_WIFI || networkInfo.type == ConnectivityManager.TYPE_MOBILE){
			refreshDisplay = true
		}else refreshDisplay = false
 	}
}
