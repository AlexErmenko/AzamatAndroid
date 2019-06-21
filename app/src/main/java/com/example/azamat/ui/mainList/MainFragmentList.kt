package com.example.azamat.ui.mainList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.beust.klaxon.Klaxon
import com.example.azamat.R
import com.example.azamat.db.entity.DeviceInfo
import kotlinx.android.synthetic.main.fragment_main_list.view.*
import kotlinx.coroutines.*
import java.net.URL


const val API_URL = "http://api.kgemt.org.ua/amat/v2/data"

class MainFragmentList : Fragment() {
	
	
	private lateinit var recyclerView: RecyclerView
	private lateinit var dataset: Array<String>
	lateinit var adapter: MainRecycleAdapter
	
	companion object {
		fun newInstance() = MainFragmentList()
	}


//	lateinit var bind :
	//TIDI
	
	lateinit var list: MutableList<String>
	lateinit var refreshLayout: SwipeRefreshLayout
	
	@ExperimentalCoroutinesApi
	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		
		val view = inflater.inflate(R.layout.fragment_main_list, container, false)
		refreshLayout = view.findViewById(R.id.swipe_container)
		
		recyclerView = view.recycler_view
		dataset = initDataset()
		
		GlobalScope.launch(Dispatchers.Main, CoroutineStart.UNDISPATCHED) {
			
			list = withContext(Dispatchers.Default) { loadData() } as MutableList<String>
			
			adapter = MainRecycleAdapter(list)
			recyclerView.adapter = adapter
			recyclerView.hasFixedSize()
			recyclerView.layoutManager = LinearLayoutManager(activity)
		}
		return view
	}
	
	suspend fun readJson() = withContext(Dispatchers.IO) {
		return@withContext URL(API_URL).readText()
	}
	
	
	private fun
			initDataset(): Array<String> {
		return Array(8) { i -> "This is element # $i" }
	}
	
	@ExperimentalCoroutinesApi
	suspend fun loadData(): List<String> = GlobalScope.async {
		val json = readJson()
		val result = Klaxon().parse<DeviceInfo>(json)
		return@async listOf(
			"${resources.getString(R.string.ilum)}: ${result?.illumination}",
			"${resources.getString(R.string.humidity)}: ${result?.humidity}",
			"${resources.getString(R.string.smoke)}: ${result?.smoke}",
			"${resources.getString(R.string.bar)}: ${result?.barometr}",
			"${resources.getString(R.string.gas)}: ${result?.gas}",
			"${resources.getString(R.string.air_quality)}: ${result?.airQual}"
		)
	}.await()
	
	@ExperimentalCoroutinesApi
	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		refreshLayout.isRefreshing = true
		refreshLayout.setOnRefreshListener {
			GlobalScope.launch(Dispatchers.Main, CoroutineStart.UNDISPATCHED) {
				refreshLayout.isRefreshing = false
				list.clear()
				
				list =
					withContext(kotlinx.coroutines.Dispatchers.Default) { loadData() }.toMutableList()
				adapter.notifyDataSetChanged()
				
			}
		}
	}
}
