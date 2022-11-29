package com.example.demo_28_11_2002

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.TextView
import java.util.*

class SelectSpinnerAdapter(context: Context,resource : Int, objects: MutableList<NoMore>) : ArrayAdapter<NoMore>(context, resource, objects) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val convertView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        val tvSelected: TextView by lazy { convertView.findViewById(R.id.tvSelector) }

            val item: NoMore = this.getItem(position)!!
            tvSelected.text = item.name

        return convertView
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val convertView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_selecter, parent, false)
        val tvViewCategory: TextView by lazy { convertView.findViewById(R.id.tvCategory) }
        val  item : NoMore = this.getItem(position)!!
        tvViewCategory.text = item.name
        return convertView
    }
}