package com.ydh.phonebook.contract

import com.ydh.phonebook.model.LoginBody
import com.ydh.phonebook.model.UserModel

interface ProfileContract {

    interface View{
        fun onSuccessLogout( message: String)
        fun onSuccessUser(name: String, email: String)
    }

    interface Presenter{
        fun userLogout()
        fun getUserInfo()
    }

}