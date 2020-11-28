package com.ydh.phonebook.presenter

import com.ydh.phonebook.App
import com.ydh.phonebook.UserSession
import com.ydh.phonebook.contract.ProfileContract
import com.ydh.phonebook.contract.RegisterContract
import com.ydh.phonebook.model.UserModel
import com.ydh.phonebook.repository.UserRemoteRepository

class ProfilePresenter (private val view: ProfileContract.View): ProfileContract.Presenter {
    private val prefs: UserSession by lazy {
        UserSession(App.instance)
    }

    override fun userLogout() {
        prefs.apply {
            token = ""
            name = ""
            email = ""
            password = ""
            loggedIn = false
        }
        view.onSuccessLogout("Successful Logout")

    }

    override fun getUserInfo() {
        view.onSuccessUser(prefs.name!!, prefs.email!!)
    }


}