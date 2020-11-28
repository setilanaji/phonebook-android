package com.ydh.phonebook.contract

import com.ydh.phonebook.model.BodyRegistration
import com.ydh.phonebook.model.LoginBody
import com.ydh.phonebook.model.UserModel

class RegisterContract {

    interface View{
        fun onSuccessRegister( message: String)
        fun onFailedRegister(message: String)

        fun loading()

    }

    interface Presenter{
        fun userRegister(registerBody: BodyRegistration)
    }
}