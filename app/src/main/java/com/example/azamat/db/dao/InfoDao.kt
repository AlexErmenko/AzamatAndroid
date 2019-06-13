package com.example.azamat.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.azamat.db.entity.Info


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
	@Query("SELECT * FROM Info")
	fun getAll(): List<Info>
	
	
	@Query("DELETE FROM Info")
	fun removeAll()


//	@Query(OnConflictStrategy = OnConflictStrategy.REPLACE, "INSERT :info  ")
//	fun insertInfo(info: Info)
	
}