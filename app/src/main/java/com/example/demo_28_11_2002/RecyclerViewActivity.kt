package com.example.demo_28_11_2002

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.demo_28_11_2002.adapter.RecyclerViewAdapter
import kotlinx.android.synthetic.main.activity_recyler_view.*

class RecyclerViewActivity : AppCompatActivity() {
    private lateinit var recyclerViewAdapter: RecyclerViewAdapter
    private lateinit var listUser : MutableList<User>
    private  var list = mutableListOf<User>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recyler_view)
        list = getAllUser()
        recyclerViewAdapter = RecyclerViewAdapter()
        recyclerViewAdapter.setData(list)
        rcvHorizontal.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        rcvHorizontal.adapter = recyclerViewAdapter
        rcvVertical.layoutManager = LinearLayoutManager(this)
        rcvVertical.adapter = recyclerViewAdapter

    }

    private fun getAllUser() : MutableList<User>{
        listUser = mutableListOf()
        listUser.add(User(1,"Huy"))
        listUser.add(User(2,"Huy1"))
        listUser.add(User(3,"Huy2"))
        listUser.add(User(4,"Huy3"))
        listUser.add(User(5,"Huy4"))
        listUser.add(User(6,"Huy5"))
        listUser.add(User(7,"Huy6"))
        listUser.add(User(8,"Huy7"))
        listUser.add(User(9,"Huy7"))
        listUser.add(User(82,"Huy7"))
        listUser.add(User(83,"Huy7"))
        listUser.add(User(81,"Huy7"))
        listUser.add(User(28,"Huy7"))
        listUser.add(User(38,"Huy7"))
        listUser.add(User(18,"Huy7"))
        listUser.add(User(38,"Huy7"))
        return listUser
    }
}