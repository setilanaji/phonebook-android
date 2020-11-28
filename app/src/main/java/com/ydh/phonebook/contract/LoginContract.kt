package com.ydh.phonebook.contract

import com.ydh.phonebook.model.ContactModel
import com.ydh.phonebook.model.LoginBody
import com.ydh.phonebook.model.UserModel

interface LoginContract {

    interface View{
        fun onSuccessLogin(userModel: UserModel, message: String)
        fun onFailedLogin(message: String)
    }

    interface Presenter{
        fun userLogin(loginBody: LoginBody)
    }

}