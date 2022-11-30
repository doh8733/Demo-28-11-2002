package com.example.demo_28_11_2002.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.demo_28_11_2002.R
import com.example.demo_28_11_2002.User
import kotlinx.android.synthetic.main.item_view_user.view.*

class RecyclerViewAdapter() : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    private  var list=  mutableListOf<User>()
    fun setData(list: MutableList<User>){
        this.list = list
        notifyDataSetChanged()
    }
    class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
       private var tvName = itemView.tvName
        fun onBindView(user: User){
            tvName.text = user.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_view_user,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBindView(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}