package com.fahimshahrierrasel.kotlinsandbox.ui.tabviewpager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.fahimshahrierrasel.kotlinsandbox.R
import com.google.android.material.tabs.TabLayout

class ViewPagerFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_viewpager, container, false)

        val pagerAdapter = ScreenSlidePagerAdapter(childFragmentManager)

        val pager = view.findViewById<ViewPager>(R.id.view_pager)
        val tablayout = view.findViewById<TabLayout>(R.id.tabs)
        pager.adapter = pagerAdapter
        tablayout.setupWithViewPager(pager)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PageFragment().apply {
                arguments = Bundle()
            }
    }

    private inner class ScreenSlidePagerAdapter(fa: FragmentManager) : FragmentStatePagerAdapter(fa) {
        override fun getItem(position: Int): Fragment =
            PageFragment()

        override fun getCount(): Int = 3

        override fun getPageTitle(position: Int): CharSequence? {
            return "Page $position"
        }
    }
}
