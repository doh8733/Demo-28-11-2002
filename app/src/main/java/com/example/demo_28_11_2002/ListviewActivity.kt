package com.example.demo_28_11_2002

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.Toast
import com.example.demo_28_11_2002.adapter.MyListviewAdapter
import kotlinx.android.synthetic.main.activity_listview.*

class ListviewActivity : AppCompatActivity() {
    private lateinit var myListviewAdapter: MyListviewAdapter
    private lateinit var listUser : MutableList<User>
    private  var list = mutableListOf<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listview)
        val lvList = findViewById<ListView>(R.id.lvList)
        list = getAllUser()
        myListviewAdapter = MyListviewAdapter()
        myListviewAdapter.setListData(list)
        lvList.adapter = myListviewAdapter
        gridView.adapter = myListviewAdapter
        myListviewAdapter.notifyDataSetChanged()
        lvList.setOnItemClickListener { parent, view, position, id ->
            Toast.makeText(this, "$id", Toast.LENGTH_SHORT).show()
        }
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