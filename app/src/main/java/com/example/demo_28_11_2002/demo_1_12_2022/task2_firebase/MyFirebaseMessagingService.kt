package com.example.demo_28_11_2002.demo_1_12_2022.task2_firebase

import android.annotation.SuppressLint
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.demo_28_11_2002.R
import com.example.demo_28_11_2002.demo_1_12_2022.task2_firebase.MyAppNotification.Companion.CHANNEL_ID
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.google.firebase.messaging.ktx.remoteMessage

@SuppressLint("MissingFirebaseInstanceTokenRefresh")
class MyFirebaseMessagingService : FirebaseMessagingService() {
    companion object{
        var name: String = MyFirebaseMessagingService::class.java.name
    }
    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
//        var notification : RemoteMessage.Notification = message.notification!!
//        val title = notification.title
//        val strmessage = notification.body

        //data message
        val strMap : MutableMap<String, String> = message.data ?: return

        val title = strMap["title"]
        val message2 = strMap["message"]
        sendNotification(title,message2)
    }

    private fun sendNotification(title: String?, strmessage: String?) {
        val i = Intent(this,NotifiCationActivity::class.java)
        val pending = PendingIntent.getActivity(this,0,i,PendingIntent.FLAG_CANCEL_CURRENT)
        val notifi :NotificationCompat.Builder = NotificationCompat.Builder(this,CHANNEL_ID)
            .setContentTitle(title).setContentText(strmessage).setSmallIcon(R.mipmap.ic_launcher)
            .setContentIntent(pending)
        val notification = notifi.build()
        val manager :NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(1,notification)

    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.e(name, "onNewToken: $token", )
    }
}