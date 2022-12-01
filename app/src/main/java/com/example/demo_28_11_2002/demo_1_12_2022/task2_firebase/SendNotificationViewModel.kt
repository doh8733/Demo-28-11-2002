package com.example.demo_28_11_2002.demo_1_12_2022.task2_firebase

import android.app.Application
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import java.lang.Exception
import java.lang.IllegalArgumentException

class SendNotificationViewModel(val app :Application):ViewModel() {

    fun sendNotification(token :String,title:String,message:String,to :String) = liveData(Dispatchers.IO) {
        emit(DataResponse.loading(data = null))
        try {
            emit(DataResponse.success(data = RetrofitClient.api.sendNotifiCation(token, DataNotification(Data(title,message),to))))
        }catch (e : Exception){
            Log.v("myLog","error ${e.message}")
            emit(DataResponse.error(data = null, message = e.message ?: "ERR"))
        }
    }
    class SendDataNotificationFactory(private val app: Application):ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(SendNotificationViewModel::class.java)) {
                return SendNotificationViewModel(app) as T
            }
            throw  IllegalArgumentException("Unable construct viewModel")
        }
    }
}