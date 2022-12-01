package com.example.demo_28_11_2002

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import com.example.demo_28_11_2002.demo_1_12_2022.task1.MainTask01Activity
import com.example.demo_28_11_2002.swingdata.activitytofragment.Main2Activity
import com.google.android.material.button.MaterialButton
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val a = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        //hello.
        btnSignIn.setOnClickListener {
           validate()
        }
    }
    private fun validate(){
        if (tilEmail.editText?.text.toString().trim().isEmpty()){
            tilEmail.error ="Khong duoc de trong"
            tilEmail.isErrorEnabled = true
            return
        }
        else{
            tilEmail.isErrorEnabled = false
            val i = Intent(this,MainTask01Activity::class.java)
            i.putExtra("name",tilEmail.editText?.text.toString().trim())
            startActivity(i)
        }
    }
}