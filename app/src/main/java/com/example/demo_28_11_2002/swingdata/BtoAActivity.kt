package com.example.demo_28_11_2002.swingdata

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.demo_28_11_2002.R
import kotlinx.android.synthetic.main.activity_ato_bactivity.*
import kotlinx.android.synthetic.main.activity_bto_aactivity.*
import kotlinx.android.synthetic.main.activity_bto_aactivity.btnSendData
import kotlinx.android.synthetic.main.activity_bto_aactivity.tvResult

class BtoAActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bto_aactivity)


        val data = intent.extras!!.getString("data")
        if (data == null){
            tvResult.text ="Result"
        }
        tvResult.text = data

        btnSendData.setOnClickListener {
            val data_b = edtDataB.text.toString()
            val i = Intent(this,AtoBActivity::class.java)
            i.putExtra("data_b",data_b)
            startActivity(i)
            finish()
        }
    }
}