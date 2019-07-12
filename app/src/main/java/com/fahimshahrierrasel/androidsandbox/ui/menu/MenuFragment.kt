package com.fahimshahrierrasel.androidsandbox.ui.menu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.fahimshahrierrasel.androidsandbox.MainActivity
import com.fahimshahrierrasel.androidsandbox.R
import com.fahimshahrierrasel.androidsandbox.ui.FragmentView
import kotlinx.android.synthetic.main.fragment_menu.*

class MenuFragment : Fragment() {

    private val rootActivity by lazy {
        activity as MainActivity
    }

    companion object {
        fun newInstance(bundle: Bundle) = MenuFragment().apply {
            arguments = bundle
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val menuList = ArrayList<Menu>()

        for (item in FragmentView.values())
            menuList.add(Menu(item.title, item))

        rv_menu.layoutManager = LinearLayoutManager(rootActivity)
        rv_menu.setHasFixedSize(true)

        val menuAdapter = MenuAdapter(menuList)
        rv_menu.adapter = menuAdapter

        menuAdapter.setOnItemClickListener { adapter, _, position ->
            val menu = adapter.getItem(position) as Menu
            rootActivity.openFragment(menu.fragmentView)
        }
    }


    inner class Menu(val title: String, val fragmentView: FragmentView)

    inner class MenuAdapter(menus: List<Menu>) :
        BaseQuickAdapter<Menu, BaseViewHolder>(R.layout.item_menu, menus) {

        override fun convert(helper: BaseViewHolder, item: Menu) {
            helper.setText(R.id.tv_menu_name, item.title)
        }
    }
}
