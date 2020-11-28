package com.ydh.phonebook.model

import com.google.gson.annotations.SerializedName

data class ResponseModel <T> (
    @SerializedName("data")
    val data: T,
    @SerializedName("status")
    val status: Boolean,
    @SerializedName("message")
    val message: String,
)