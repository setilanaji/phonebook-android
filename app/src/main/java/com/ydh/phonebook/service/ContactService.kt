package com.ydh.phonebook.service

import com.ydh.phonebook.model.ContactModel
import com.ydh.phonebook.model.ResponseModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface ContactService {

    @GET("api/v1/contacts")
    fun getAllContact(
        @Header("Authorization")
        token: String
    ): Call<ResponseModel<List<ContactModel>>>
}