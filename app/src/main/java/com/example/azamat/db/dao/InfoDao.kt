package com.example.azamat.db.dao

import android.icu.text.IDNA
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.azamat.db.entity.InfoEntity


/*
* @InfoDao
*
* */
@Dao
interface InfoDao {
	
	/*
	* Method for get all information
	* @return all info from table Info
	* */
	@Query("SELECT * FROM InfoEntity")
	fun getAll(): List<IDNA.Info>
	
	
	@Query("DELETE FROM InfoEntity")
	fun removeAll()
	
	
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	fun append(info: InfoEntity)
	
	
	@Query("SELECT * FROM INFOENTITY")
	fun loadAllInfo(): LiveData<List<InfoEntity>>
	
}