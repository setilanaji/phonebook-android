package com.ydh.phonebook.repository

import com.ydh.phonebook.model.ContactModel
import com.ydh.phonebook.model.ResponseModel
import com.ydh.phonebook.service.ContactService
import okhttp3.ResponseBody
import retrofit2.Call

class ContactRemoteRepository (private val service: ContactService){


    fun getAllContact(token: String ): Call<ResponseModel<List<ContactModel>>>{
        return service.getAllContact(token)
    }

    fun deleteContact(token: String, id: Int): Call<ResponseModel<String>>{
        return  service.deleteContact(token, id)
    }

}