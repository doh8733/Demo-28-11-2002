package com.example.demo_28_11_2002.demo_1_12_2022.task2_firebase

import retrofit2.Response
import retrofit2.http.*

interface TodoApi {
//day la data dang json nen su dung nhu the nay
    @POST("send")
    @Headers("Content-Type:application/json")
    suspend fun sendNotifiCation(
        @Header("Authorization") token :String,
        @Body dataNotification : DataNotification

    ):Response<DataNotificationResponse>

}