package com.ydh.phonebook.model

import com.google.gson.annotations.SerializedName

data class UserModel (
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    var name: String,
    @SerializedName("email")
    var email: String,
    @SerializedName("token")
    val token: String,
    @SerializedName("password")
    val password: String
)