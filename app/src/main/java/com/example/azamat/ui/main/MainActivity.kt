package com.example.azamat.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.azamat.R
import com.example.azamat.databinding.ActivityMainBinding
import com.example.azamat.db.AppDatabase
import com.example.azamat.db.repository.DeviceInfoRepository
import com.google.android.material.navigation.NavigationView
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
	
	private lateinit var appBarConfiguration: AppBarConfiguration
	private lateinit var drawerLayout: DrawerLayout
	private lateinit var navView: NavigationView
	private lateinit var navController: NavController
	lateinit var rep: DeviceInfoRepository
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		
		val binding: ActivityMainBinding =
			DataBindingUtil.setContentView(this, R.layout.activity_main)
		drawerLayout = binding.drawerLayout
		navView = binding.navigationView
		navController = Navigation.findNavController(this, R.id.hostFragment)
		
		appBarConfiguration = AppBarConfiguration(navController.graph, drawerLayout)
		
		setSupportActionBar(binding.toolbar)
		setupActionBarWithNavController(navController, appBarConfiguration)
		binding.navigationView.setupWithNavController(navController)
		
		
		val db = AppDatabase.getInstance(this)
		val dao = db.infoDao()
		var d: Int? = null
		rep = DeviceInfoRepository(dao)
		GlobalScope.launch(Dispatchers.Main, CoroutineStart.UNDISPATCHED) {
			d = rep.load()
		}
		
		
		
		
		
	}
	
	override fun onSupportNavigateUp(): Boolean {
		val navController = findNavController(R.id.hostFragment)
		return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
	}
	
	override fun onBackPressed(): Unit = when {
		drawerLayout.isDrawerOpen(GravityCompat.START) -> drawerLayout.closeDrawer(GravityCompat.START)
		else                                           -> super.onBackPressed()
	}
	
	
}
