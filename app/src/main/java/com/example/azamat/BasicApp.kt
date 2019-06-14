package com.example.azamat

import android.app.Application
import com.example.azamat.db.AppDatabase


class BasicApp : Application() {
	
	private var appExecutor: AppExecutor? = null
	
	
	val db: AppDatabase
		get() = AppDatabase.getInstance(this, appExecutor)
	
	val repository: DataRepository
		get() = DataRepository.getInstance(db)
	
	override fun onCreate() {
		super.onCreate()
		appExecutor = AppExecutor()
	}
}
