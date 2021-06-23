package com.bigstep.assignment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.viewpager2.widget.ViewPager2
import com.bigstep.assignment.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private val arrayTabs = arrayOf("Videos", "History")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        initUI()

        println("array tab size ${arrayTabs.size}")

    }

    private fun setPagerAdapter() {
        val adapter = SliderPagerAdapter(supportFragmentManager, lifecycle)
        binding.pager.adapter = adapter
        binding.pager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                println("MainActivity.onPageSelected $position")
            }

        })
    }

    private fun initUI() {
        setPagerAdapter()
        TabLayoutMediator(binding.tabs, binding.pager) { tab, position ->
            tab.text = arrayTabs[position]
        }.attach()
    }


}