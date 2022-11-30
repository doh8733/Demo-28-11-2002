package com.example.demo_28_11_2002.swingdata.fragmenttofragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentTransaction
import com.example.demo_28_11_2002.R

class QuestionActivity : AppCompatActivity(),Fragment1.ISendDataListener,Fragment2.IUpdateData {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)
      val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
          transaction.add(R.id.container1,Fragment1())
          transaction.add(R.id.container2,Fragment2())
          transaction.commit()

    }

    //ham trung gian truyen va nhan data giua fragment 1 va 2
    override fun sendData(email: String) {
        val fragment2 :Fragment2 = supportFragmentManager.findFragmentById(R.id.container2) as Fragment2
        fragment2.receiveDataFromFragment1(email)
    }
    //ham trung gian truyen va nhan data giua fragment 2 va 1
    override fun onUpDate(email: String) {
        val fragment1 : Fragment1 = supportFragmentManager.findFragmentById(R.id.container1) as Fragment1
        fragment1.receiveDataFromFragment2(email)
    }
}