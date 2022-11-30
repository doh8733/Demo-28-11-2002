package com.example.demo_28_11_2002.swingdata.activitytofragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.demo_28_11_2002.R
import kotlinx.android.synthetic.main.activity_main2.*

class Main2Activity : AppCompatActivity(),Fragment5.ISendDataListener2 {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        btnSendToFragment.setOnClickListener {
            val fragmentManager : FragmentManager = supportFragmentManager
            val transaction : FragmentTransaction= fragmentManager.beginTransaction()
            val bundle = Bundle()
            val text = edtSendData.text.toString()
            bundle.putString("message",text)
            val fragment5  = Fragment5()
            fragment5.arguments = bundle
            transaction.replace(R.id.containerView,fragment5).commit()
        }
    }

    override fun sendDataFromFragment(data: String) {
        edtSendData.setText(data)
    }
}