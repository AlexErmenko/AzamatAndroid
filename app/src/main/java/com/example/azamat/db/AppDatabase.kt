package com.example.azamat.db

import android.content.Context
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.azamat.AppExecutor
import com.example.azamat.db.dao.DeviceInfoDao
import com.example.azamat.db.entity.DeviceInfo

@Database(entities = [DeviceInfo::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
	
	private val IsDatabaseCreated = MutableLiveData<Boolean>()
	
	val databaseCreated: LiveData<Boolean>
		get() = IsDatabaseCreated
	
	private fun updateDatabaseCreated(context: Context) {
		if (context.getDatabasePath(DATABASE_NAME).exists()) {
			setDatabaseCreated()
		}
	}
	
	private fun setDatabaseCreated() {
		IsDatabaseCreated.postValue(true)
	}
	
	
	abstract fun infoDao(): DeviceInfoDao
	
	companion object {
		@VisibleForTesting
		val DATABASE_NAME = "AzamatDatabase"
		
		
		@Volatile
		private var INSTANCE: AppDatabase? = null
		
		fun getInstance(context: Context, executor: AppExecutor): AppDatabase? {
			if (INSTANCE == null) {
				synchronized(AppDatabase::class.java) {
					if (INSTANCE == null) {
						INSTANCE = buildDatabase(context.applicationContext, executor)
						INSTANCE !!.updateDatabaseCreated(context.applicationContext)
					}
				}
				
			}
			return INSTANCE
		}
		
		private fun buildDatabase(context: Context, executor: AppExecutor) =
			Room.databaseBuilder(
				context.applicationContext,
				AppDatabase::class.java, "App.db"
			)
				.build()
	}
	
}