package com.ydh.phonebook.service

import com.ydh.phonebook.model.ContactModel
import com.ydh.phonebook.model.ResponseModel
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

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

    @Multipart
    @POST("api/v1/contacts")
    fun addContact(
        @Header("Authorization")
        token: String,
        @Part("name")
        name: RequestBody,
        @Part("phone")
        phone: RequestBody,
        @Part("email")
        email: RequestBody,
        @Part("job")
        job: RequestBody,
        @Part("company")
        company: RequestBody,
        @Part("image")
        image: RequestBody? = null,
        ): Call<ResponseModel<ContactModel>>

    @Multipart
    @PUT("api/v1/contacts/{id}")
    fun updateContact(
        @Header("Authorization")
        token: String,
        @Path("id")
        id: Int,
        @Part("name")
        name: RequestBody,
        @Part("phone")
        phone: RequestBody,
        @Part("email")
        email: RequestBody,
        @Part("job")
        job: RequestBody,
        @Part("company")
        company: RequestBody,
        @Part("image")
        image: RequestBody? = null,
    ): Call<ResponseModel<ContactModel>>
}