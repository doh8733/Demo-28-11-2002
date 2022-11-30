package com.example.demo_28_11_2002.swingdata

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import com.example.demo_28_11_2002.R
import com.example.demo_28_11_2002.swingdata.activitytofragment.Main2Activity
import com.example.demo_28_11_2002.swingdata.fragmenttofragment.QuestionActivity
import com.example.demo_28_11_2002.swingdata.fragmenttofragment.p2.ParentActivity
import kotlinx.android.synthetic.main.activity_ato_bactivity.*
import kotlinx.android.synthetic.main.activity_ato_bactivity.view.*

class AtoBActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ato_bactivity)

        val data = intent.extras?.getString("data_b")
        if (data == null){
            tvResult.text ="Result"
        }
        tvResult.text = data
        btnNext.setOnClickListener {
            startActivity(Intent(this,QuestionActivity::class.java))
        }
        btnNext2.setOnClickListener {
            startActivity(Intent(this,ParentActivity::class.java))
        }
        btnNext3.setOnClickListener {
            startActivity(Intent(this,Main2Activity::class.java))

        }

        btnSendData.setOnClickListener {
            val dataA = edtDataA.text.toString()
            val i = Intent(this, BtoAActivity::class.java)
            i.putExtra("data",dataA)
            startActivity(i)
            finish()
        }
    }
}