package com.example.azamat

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.example.azamat.MainActivity.Companion.refreshDisplay

class NetworkReceiver : BroadcastReceiver() {
	
	override fun onReceive(context: Context, intent: Intent) {
		
		val conn = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
		val networkInfo: NetworkInfo? = conn.activeNetworkInfo
		refreshDisplay = when {
			networkInfo?.type == ConnectivityManager.TYPE_WIFI -> true
			networkInfo != null                                -> true
			else                                               -> false
		}
		
		// This method is called when the BroadcastReceiver is receiving an Intent broadcast.
		TODO("NetworkReceiver.onReceive() is not implemented")
	}
}
