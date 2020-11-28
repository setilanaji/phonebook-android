package com.ydh.phonebook.service

import com.ydh.phonebook.model.ContactModel
import com.ydh.phonebook.model.ResponseModel
import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface ContactService {

    @GET("api/v1/contacts")
    fun getAllContact(
        @Header("Authorization")
        token: String
    ): Call<ResponseModel<List<ContactModel>>>

    @DELETE("api/v1/contacts/{id}")
    fun deleteContact(
            @Header("Authorization")
            token: String,
            @Path("id")
            id: Int
    ): Call<ResponseModel<String>>
}