package com.example.azamat.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.azamat.db.dao.DeviceInfoDao
import com.example.azamat.db.entity.DeviceInfo

const val DATABASE_NAME = "AzamatDatabase"

@Database(entities = [DeviceInfo::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
	abstract fun infoDao(): DeviceInfoDao
	
	companion object {
		@Volatile
		private var INSTANCE: AppDatabase? = null
		
		fun getInstance(context: Context): AppDatabase =
			INSTANCE ?: synchronized(this) {
				INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
			}
		
		private fun buildDatabase(context: Context) =
			Room.databaseBuilder(
				context.applicationContext,
				AppDatabase::class.java, DATABASE_NAME
			)
				.build()
	}
	
}