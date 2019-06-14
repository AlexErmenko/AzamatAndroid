package com.example.azamat.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class InfoEntity(
	@PrimaryKey
	@ColumnInfo(name = "ID")
	var id: Int,
	@ColumnInfo(name = "Name")
	var name: String,
	@ColumnInfo(name = "Value")
	var value: Any
)
