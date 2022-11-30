package com.example.demo_28_11_2002.adapter

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.demo_28_11_2002.R
import com.example.demo_28_11_2002.User
import kotlinx.android.synthetic.main.item_user.view.*

class MyListviewAdapter : BaseAdapter() {
    var list = mutableListOf<User>()
    fun setListData(list: MutableList<User>){
        this.list = list
        notifyDataSetChanged()
    }
    override fun getCount(): Int = list.size

    override fun getItem(position: Int): Any {
        return list[position]
    }

    override fun getItemId(position: Int): Long {
        return list[position].id.toLong()
    }

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val item = list[position]
        val convertView = View.inflate(parent?.context, R.layout.item_user,null)
        convertView.tvId.text = item.id.toString()
        convertView.tvName.text = item.name

        return convertView
    }
}