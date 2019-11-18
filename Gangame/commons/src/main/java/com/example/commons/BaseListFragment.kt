package com.example.commons

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_list.view.*

abstract class BaseListFragment : BaseFragment() {

    val listAdapter: RecyclerView.Adapter<*>
        get() = getAdapter()

    override fun getLayoutResId(): Int {
        return R.layout.fragment_list
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //view.list?.adapter = listAdapter
        //view.list.layoutManager = LinearLayoutManager(context)

        view.list.let {
            with(view.list) {
                adapter = listAdapter
                layoutManager = LinearLayoutManager(context)
            }
        }
    }

    abstract fun getAdapter(): RecyclerView.Adapter<*>

}