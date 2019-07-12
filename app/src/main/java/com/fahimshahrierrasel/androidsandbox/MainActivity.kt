package com.fahimshahrierrasel.androidsandbox

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.fahimshahrierrasel.androidsandbox.ui.FragmentView
import com.fahimshahrierrasel.androidsandbox.ui.menu.MenuFragment
import com.fahimshahrierrasel.androidsandbox.ui.tabviewpager.ViewPagerFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme_NoActionBar)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        openFragment(FragmentView.MENU)
    }

    fun openFragment(fragmentView: FragmentView) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()

        val fragment = when (fragmentView) {
            FragmentView.VIEWPAGER -> ViewPagerFragment.newInstance(Bundle())
            FragmentView.MENU -> MenuFragment.newInstance(Bundle())
        }
        if (fragmentView != FragmentView.MENU)
            fragmentTransaction.addToBackStack(null)

        fragmentTransaction
            .replace(R.id.main_fragment_placeholder, fragment)
            .commit()
    }

}
