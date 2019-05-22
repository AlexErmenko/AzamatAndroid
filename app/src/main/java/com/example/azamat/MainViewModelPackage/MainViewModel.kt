package com.example.azamat.MainViewModelPackage

import android.content.Context
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import java.net.URL

class MainViewModel : ViewModel() {
	private val viewModelJob = SupervisorJob()
	private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
	
	override fun onCleared() {
		super.onCleared()
		viewModelJob.cancel()
	}
	
	val item get() = android.R.layout.simple_list_item_1
	
	
	
	fun loadList() = mutableListOf<String>("1", "2", "3")
	
	fun loadListView(listView: ListView, context: Context) {
		uiScope.launch {
			listView.adapter = ArrayAdapter(context, item, loadList())
		}
	}
	
	
	suspend fun parseJson(url: String) = withContext(Dispatchers.Default) {
		URL(url).readText()
	}
}