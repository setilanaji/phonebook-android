package com.ydh.phonebook.repository

import com.ydh.phonebook.model.LoginBody
import com.ydh.phonebook.model.ResponseModel
import com.ydh.phonebook.model.UserModel
import com.ydh.phonebook.service.UserService
import retrofit2.Call

class UserRemoteRepository (private val service: UserService){

    fun userLogin(loginBody: LoginBody): Call<ResponseModel<UserModel>>{
        return service.userLogin(loginBody)
    }
}