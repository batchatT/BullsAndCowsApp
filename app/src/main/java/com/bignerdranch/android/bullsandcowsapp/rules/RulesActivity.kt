package com.bignerdranch.android.bullsandcowsapp.rules

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.bignerdranch.android.bullsandcowsapp.R

class RulesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rules)
        val viewPager = findViewById<ViewPager>(R.id.viewPager)
        viewPager.adapter = RulesPageAdapter(supportFragmentManager)
    }
}