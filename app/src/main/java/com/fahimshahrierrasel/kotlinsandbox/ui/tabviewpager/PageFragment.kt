package com.fahimshahrierrasel.kotlinsandbox.ui.tabviewpager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.fahimshahrierrasel.kotlinsandbox.R
import kotlinx.android.synthetic.main.fragment_page.*

class PageFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler_view.layoutManager = LinearLayoutManager(activity)
        recycler_view.setHasFixedSize(true)
        val names = ArrayList<String>()
        for (i in 1..20) {
            names.add("Person $i")
        }

        recycler_view.adapter = NameAdapter(names)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PageFragment().apply {
                arguments = Bundle()
            }
    }

    inner class NameAdapter(names: List<String>) :
        BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_recycler_view, names) {

        override fun convert(helper: BaseViewHolder, item: String) {
            helper.setText(R.id.textView, item)
            helper.setText(R.id.textView2, "O ever youthful,O ever weeping")

        }
    }
}
