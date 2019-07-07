package com.fahimshahrierrasel.kotlinsandbox

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.fahimshahrierrasel.kotlinsandbox.ui.tabviewpager.TabViewPagerActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme_NoActionBar)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        val menuList = ArrayList<Menu>()
        menuList.add(Menu("Tab View Pager", TabViewPagerActivity::class.java))

        rv_menu.layoutManager = LinearLayoutManager(this)
        rv_menu.setHasFixedSize(true)

        val menuAdapter = MenuAdapter(menuList)
        rv_menu.adapter = menuAdapter

        menuAdapter.setOnItemClickListener { _, _, position ->
            startActivity(Intent(this, menuList[position].activity))
        }
    }

    inner class Menu(val title: String, val activity: Class<TabViewPagerActivity>)

    inner class MenuAdapter(menus: List<Menu>) :
            BaseQuickAdapter<Menu, BaseViewHolder>(R.layout.item_menu, menus) {

        override fun convert(helper: BaseViewHolder, item: Menu) {
            helper.setText(R.id.tv_menu_name, item.title)
        }
    }
}
