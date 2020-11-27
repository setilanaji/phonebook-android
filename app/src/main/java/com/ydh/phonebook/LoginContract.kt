package com.ydh.phonebook

import com.ydh.phonebook.model.ContactModel
import com.ydh.phonebook.model.LoginBody

interface LoginContract {

    interface View{
        fun onSuccessLogin(token: String)
        fun onSuccessGetContact(list: List<ContactModel>)
    }

    interface Presenter{
        fun userLogin(loginBody: LoginBody)
        fun getAllContact()
    }

}