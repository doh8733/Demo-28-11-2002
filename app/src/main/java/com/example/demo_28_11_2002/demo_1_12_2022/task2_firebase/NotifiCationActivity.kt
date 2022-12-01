package com.example.demo_28_11_2002.demo_1_12_2022.task2_firebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.demo_28_11_2002.R
import com.example.demo_28_11_2002.demo_1_12_2022.task2_firebase.ApiStatus.*
import kotlinx.android.synthetic.main.activity_notifi_cation.*

class NotifiCationActivity : AppCompatActivity() {
    private val sendNotificationViewModel :SendNotificationViewModel by lazy {
        ViewModelProvider(
            this,SendNotificationViewModel.SendDataNotificationFactory(this.application)
        )[SendNotificationViewModel::class.java]
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notifi_cation)
        val sdf = getSharedPreferences("MYTOKEN", MODE_PRIVATE)
        val url = sdf.getString("token","")
        edTokenDevice.setText(url)

        btnSendNotification.setOnClickListener {
            var token ="key=AAAAko-4tIo:APA91bHYZU5GD7O60rW8jgWtJ8A0GJp23NPaTGY-3nghnCyxqxSdmeYkXJD0GD4NOvhokKlMQPmTULV_whUFhSmAGZXpsGJhlYUFkcT7jsq0okYcxA6krzRQj5iK2v3claYilAq8gGzF"
            val title = edTitle.text.toString()
            val message = edMessage.text.toString()
            val to = edTokenDevice.text.toString().trim()
            sendNotificationViewModel.sendNotification(token,title, message, url!!).observe(this){
                it.let {
                    when(it.resStatus){
                        SUCCESS -> {
                            if (it.data?.code() == 200){
                                Toast.makeText(this, "Thanh cong", Toast.LENGTH_SHORT).show()
                            }
                            else{
                                Toast.makeText(this, it.data?.message(), Toast.LENGTH_SHORT).show()
                                Log.e("TAG", "onCreate: ${it.data?.message()}", )
                            }
                        }
                        ERROR ->   {
                            Toast.makeText(this, it.data?.message(), Toast.LENGTH_SHORT).show()
                            Log.e("TAG", "onCreate2: ${it.message}", )

                        }
                        LOADING -> {

                        }
                    }
                }
            }
        }


    }
}