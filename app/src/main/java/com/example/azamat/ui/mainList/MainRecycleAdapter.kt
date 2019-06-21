package com.example.azamat.ui.mainList

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.azamat.R
import kotlinx.android.synthetic.main.main_item_list.view.*

class MainRecycleAdapter(private val dataSet: List<String>) :
	RecyclerView.Adapter<MainRecycleAdapter.ViewHolder>() {
	
	
	class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
		
		val cardView: CardView
		//		val cardTitle: TextView
		val cardText: TextView
		
		init {
			// Define click listener for the ViewHolder's View.
			cardView = v.card_view
			cardText = v.cardText
			cardView.setOnClickListener {
			
			}
		}
	}
	
	// Create new views (invoked by the layout manager)
	override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
		// Create a new view.
		val v = LayoutInflater.from(viewGroup.context)
			.inflate(R.layout.main_item_list, viewGroup, false)
		
		return ViewHolder(v)
	}
	
	// Replace the contents of a view (invoked by the layout manager)
	override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
		Log.d(TAG, "Element $position set.")
		
		// Get element from your dataset at this position and replace the contents of the view
		// with that element
//		viewHolder.cardTitle.text = dataSet[position]
		viewHolder.cardText.text = dataSet[position]
		
		
		viewHolder.cardView.setOnClickListener {
			/*Snackbar.make(it," Hello bitch", Snackbar.LENGTH_SHORT).show()*/
			
			
			
			
			
		}
	}
	
	// Return the size of your dataset (invoked by the layout manager)
	override fun getItemCount() = dataSet.size
	
	companion object {
		private val TAG = "CustomAdapter"
	}
}