package com.example.azamat.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.azamat.db.dao.InfoDao
import com.example.azamat.db.entity.Info

@Database(entities = [Info::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
	
	
	abstract fun infoDao(): InfoDao
	
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
				AppDatabase::class.java, "App.db"
			)
				.build()
	}
	
}