package com.ydh.phonebook.repository

import com.ydh.phonebook.model.ContactModel
import com.ydh.phonebook.model.ResponseModel
import com.ydh.phonebook.service.ContactService
import okhttp3.ResponseBody
import retrofit2.Call

class ContactRemoteRepository (private val service: ContactService){


    fun getAllContact(): Call<ResponseModel<List<ContactModel>>>{
        return service.getAllContact(
            "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJlbWFpbCI6ImwyMDAxNDAwMDRAZ21haWwuY29tIiwicGFzc3dvcmQiOiIyNjJlZTQwMjcxYzdkZDMyM2EzZWNmNDIwMjg3ZjRhYyIsImlhdCI6MTYwNjQ4NjQxNiwiZXhwIjoxNjA2NTcyODE2fQ.udh48N06Mg0FIHYKZ7taCH_PFsbT7HwzxHSIY95QRxY"
        )
    }

}