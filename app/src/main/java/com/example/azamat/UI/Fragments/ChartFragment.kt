package com.example.azamat.UI.Fragments

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.beust.klaxon.Klaxon
import com.example.azamat.DeviceInfo
import com.example.azamat.R
import com.example.azamat.R.layout
import com.example.azamat.URL_API
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Math.sin
import java.net.URL
import java.util.*


/**
 * A simple [Fragment] subclass.
 */

const val count = 30

// TODO: Дописать реализацю построения графика в реальном времени
class ChartFragment : Fragment() {
	
	val handler = Handler()
	
	lateinit var mainGraphView: GraphView
	lateinit var graphView: GraphView
	
	
	lateinit var mainSeries: LineGraphSeries<DataPoint>
	lateinit var series: LineGraphSeries<DataPoint>
	
	private var graph2LastXValue = 5.0
	
	
	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		val view = inflater.inflate(layout.fragment_chart, container, false)
		
		
		mainSeries = LineGraphSeries()
		
		mainGraphView = view.findViewById(R.id.mainGraphView)
		
		mainGraphView.apply {
			viewport.isScalable = true
			addSeries(mainSeries)
		}
		
		graphView = view.findViewById(R.id.graphView)
		series = LineGraphSeries()
		
		graphView.apply {
			viewport.isXAxisBoundsManual = true
			viewport.setMinX(0.0)
			viewport.setMaxX(40.0)
		}.addSeries(series)
		
		return view
	}
	
	override fun onResume() {
		super.onResume()
		val obj = object : Runnable {
			override fun run() {
				mainSeries.resetData(arrayOf<DataPoint>())
				handler.postDelayed(this, 300)
			}
		}
		handler.postDelayed(obj, 300)
		val obj2 = object : Runnable {
			override fun run() {
				graph2LastXValue += 1.0
				series.appendData(DataPoint(graph2LastXValue, 10.0), true, 40)
				handler.postDelayed(this, 200)
			}
		}
		handler.postDelayed(obj2, 1000)
	}
	
	
	private fun generateData(): Array<DataPoint?> =
		arrayOfNulls<DataPoint>(size = count).also {
			(0 until count).forEach { i ->
				val x = i.toDouble()
				val f = mRand.nextDouble() * 0.15 + 0.3
				val y = sin(i * f + 2) + mRand.nextDouble() * 0.3
				val v = DataPoint(x, y)
				it[i] = v
			}
		}
	
	var mLastRandom = 2.0
	var mRand = Random()
	
	private fun getRandom() {
		mLastRandom += mRand.nextDouble() * 0.5 - 0.25
	}
	
	
	//Читаем Json на потоке для I/O операций
	suspend fun parseJson() = withContext(Dispatchers.IO) {
		URL(URL_API).readText()
	}
	
	//Конвертируем полученый Json в обьект на потоке для CPU операций
	suspend fun convertFromJson(stringJson: String) = withContext(Dispatchers.Default) {
		Klaxon().parse<DeviceInfo>(stringJson)
	}
	
	
}





