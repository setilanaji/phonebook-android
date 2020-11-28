package com.ydh.phonebook.repository

import com.ydh.phonebook.model.BodyAddContact
import com.ydh.phonebook.model.ContactModel
import com.ydh.phonebook.model.ResponseModel
import com.ydh.phonebook.service.ContactService
import okhttp3.MediaType
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call

class ContactRemoteRepository (private val service: ContactService){


    fun getAllContact(token: String ): Call<ResponseModel<List<ContactModel>>>{
        return service.getAllContact(token)
    }

    fun deleteContact(token: String, id: Int): Call<ResponseModel<String>>{
        return  service.deleteContact(token, id)
    }

    fun addContact(token: String, bodyAddContact: BodyAddContact): Call<ResponseModel<ContactModel>>{
        return service.addContact(
            token,
            RequestBody.create(MediaType.parse("text/plain"), bodyAddContact.name),
            RequestBody.create(MediaType.parse("text/plain"), bodyAddContact.phone),
            RequestBody.create(MediaType.parse("text/plain"), bodyAddContact.email),
            RequestBody.create(MediaType.parse("text/plain"), bodyAddContact.job),
            RequestBody.create(MediaType.parse("text/plain"), bodyAddContact.company)
        )
    }

    fun updateContact(token: String, id: Int, bodyAddContact: BodyAddContact): Call<ResponseModel<ContactModel>>{
        return service.updateContact(
            token,
            id,
            RequestBody.create(MediaType.parse("text/plain"), bodyAddContact.name),
            RequestBody.create(MediaType.parse("text/plain"), bodyAddContact.phone),
            RequestBody.create(MediaType.parse("text/plain"), bodyAddContact.email),
            RequestBody.create(MediaType.parse("text/plain"), bodyAddContact.job),
            RequestBody.create(MediaType.parse("text/plain"), bodyAddContact.company)
        )
    }

}