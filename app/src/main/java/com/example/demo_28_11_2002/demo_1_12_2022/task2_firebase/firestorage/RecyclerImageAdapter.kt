package com.example.demo_28_11_2002.demo_1_12_2022.task2_firebase.firestorage

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.demo_28_11_2002.R
import com.example.demo_28_11_2002.adapter.RecyclerViewAdapter
import kotlinx.android.synthetic.main.row_image.view.*

class RecyclerImageAdapter : RecyclerView.Adapter<RecyclerImageAdapter.ViewHolder>() {
    var listImage = mutableListOf<Image>()
    fun setData(list: MutableList<Image>){
        this.listImage = list
        notifyDataSetChanged()
    }
    class ViewHolder(view: View): RecyclerViewAdapter.ViewHolder(view) {

        fun onBindViews(image : Image){
            val url = itemView.imgView
            Glide.with(itemView.context).load(image.imageUrl).into(url)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_image,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBindViews(listImage[position])
    }

    override fun getItemCount(): Int {
       return listImage.size
    }
}