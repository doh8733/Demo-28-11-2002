package com.example.demo_28_11_2002.swingdata.fragmenttofragment.p2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentTransaction
import com.example.demo_28_11_2002.R
import com.example.demo_28_11_2002.swingdata.fragmenttofragment.Fragment1
import com.example.demo_28_11_2002.swingdata.fragmenttofragment.Fragment2
import kotlinx.android.synthetic.main.activity_parent.*

class ParentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parent)
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.layoutContainer, Fragment3())
        transaction.commit()
        btnFragment1.setOnClickListener {
            val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.layoutContainer, Fragment3())
            transaction.addToBackStack(null)
            transaction.commit()

        }
        btnFragment2.setOnClickListener {
            val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
            transaction.add(R.id.layoutContainer, Fragment4())
            transaction.addToBackStack(null)
            transaction.commit()
        }

    }
}