package com.example.demo_28_11_2002.demo_1_12_2022.task2_firebase

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build

class MyAppNotification  : Application() {
    companion object{
         var CHANNEL_ID = "NOTIFICATION_ID"
    }
    override fun onCreate() {
        super.onCreate()
        createChannelNotification()
    }

    private fun createChannelNotification() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            //dang ky channel id

            val channel = NotificationChannel(CHANNEL_ID,"notification",NotificationManager.IMPORTANCE_DEFAULT)
            val manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(channel)
        }
    }
}