package com.example.azamat.ui.onboarding

import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.azamat.R
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator
import com.tbuonomo.viewpagerdotsindicator.SpringDotsIndicator
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator

class BenefitActivity : AppCompatActivity() {
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)


//		val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)

//		val viewPager: ViewPager = findViewById(R.id.view_pager)
//		viewPager.adapter = sectionsPagerAdapter
		
		
		requestWindowFeature(Window.FEATURE_NO_TITLE)
		window.setFlags(
			WindowManager.LayoutParams.FLAG_FULLSCREEN,
			WindowManager.LayoutParams.FLAG_FULLSCREEN
		)
		setContentView(R.layout.activity_benefit)
		val dotsIndicator = findViewById<DotsIndicator>(R.id.dots_indicator)
		val springDotsIndicator = findViewById<SpringDotsIndicator>(R.id.spring_dots_indicator)
		val wormDotsIndicator = findViewById<WormDotsIndicator>(R.id.worm_dots_indicator)
		
		val viewPager = findViewById<ViewPager>(R.id.view_pager)
		val adapter = DotIndicatorPagerAdapter()
		viewPager.adapter = adapter
		viewPager.setPageTransformer(true, ZoomOutPageTransformer())
		
		dotsIndicator.setViewPager(viewPager)
		springDotsIndicator.setViewPager(viewPager)
		wormDotsIndicator.setViewPager(viewPager)


//		val tabs: TabLayout = findViewById(R.id.tabs)
//		tabs.setupWithViewPager(viewPager)
//		val fab: FloatingActionButton = findViewById(R.id.fab)

//		fab.setOnClickListener { view ->
//			Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//				.setAction("Action", null).show()
		}
	}
