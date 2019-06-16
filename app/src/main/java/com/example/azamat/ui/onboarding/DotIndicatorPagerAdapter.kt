package com.example.azamat.ui.onboarding

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.example.azamat.R
import java.util.*


class DotIndicatorPagerAdapter : PagerAdapter() {
	
	override fun instantiateItem(container: ViewGroup, position: Int): Any {
		val item =
			LayoutInflater.from(container.context)
				.inflate(R.layout.fragment_benefit, container, false)
		//CardView cardView = item.findViewById(R.id.card_view);
		//cardView.setCardBackgroundColor(
		//    ContextCompat.getColor(container.getContext(), (items.get(position).color)));
		container.addView(item)
		return item
	}
	
	override fun getCount(): Int {
		return items.size
	}
	
	override fun isViewFromObject(view: View, `object`: Any): Boolean {
		return view === `object`
	}
	
	override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
		container.removeView(`object` as View)
	}
	
	private class Item(private val color: Int)
	
	companion object {
		private val items = Arrays.asList(
			Item(R.color.md_indigo_500),
			Item(R.color.md_green_500),
			Item(R.color.md_red_500),
			Item(R.color.md_orange_500),
			Item(R.color.md_purple_500)
		)
	}
	
	
}