package com.fahimshahrierrasel.kotlinsandbox.ui.tabviewpager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.fahimshahrierrasel.kotlinsandbox.R

class TabViewPagerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tab_view_pager)

        val viewPagerFragment = ViewPagerFragment()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_fragment_placeholder, viewPagerFragment)
            .commit()

    }
}
