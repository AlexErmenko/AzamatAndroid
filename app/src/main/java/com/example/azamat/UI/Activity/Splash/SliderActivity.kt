package com.example.azamat.UI.Activity.Splash

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.azamat.R
import kotlinx.android.synthetic.main.activity_splash.*


// TODO: Сделать слайдер при загрузке в 1й раз
class SliderActivity : AppCompatActivity() {
	
	private lateinit var viewPager: ViewPager
	private lateinit var pagerAdapter: SliderPagerAdapter
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_splash)
//		val manager = PermissionChecker
		scanQR.setOnClickListener {
		
		}
		pagerAdapter = SliderPagerAdapter(supportFragmentManager, 1)
		viewPager.adapter = pagerAdapter
		
		
	}
	
	
}
