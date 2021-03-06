package com.ydh.phonebook.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ContactModel (
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    var name: String = "",
    @SerializedName("phone")
    var phone: String = "",
    @SerializedName("job")
    var job: String = "",
    @SerializedName("company")
    var company: String = "",
    @SerializedName("email")
    var email : String = "",
    @SerializedName("userId")
    val userId: Int,
    @SerializedName("image")
    var image: String?,
    @SerializedName("imageName")
    var imageName: String?,
): Parcelable