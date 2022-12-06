package com.example.demo_28_11_2002

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("id")
    var id: Int = 0,
    @SerializedName("username")
    var name: String = "",
    @SerializedName("profile_picture")
    val image: String = "",
    @SerializedName("full_name")
    val fullName: String = ""
) {
}