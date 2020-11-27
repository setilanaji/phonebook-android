package com.ydh.phonebook.service

import com.ydh.phonebook.model.LoginBody
import com.ydh.phonebook.model.ResponseModel
import com.ydh.phonebook.model.UserModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UserService {

    @POST("api/v1/signin")
    fun userLogin(
        @Body
        loginBody: LoginBody
    ): Call<ResponseModel<UserModel>>

//    @POST("api/v1/signup")
//    fun userRegister(
//        @Body
//        registration: BodyRegistration
//    ): Call<ResponseModel<String>>

}