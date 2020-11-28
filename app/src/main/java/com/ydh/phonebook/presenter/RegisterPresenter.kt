package com.ydh.phonebook.presenter

import com.google.gson.Gson
import com.ydh.phonebook.App
import com.ydh.phonebook.UserSession
import com.ydh.phonebook.contract.RegisterContract
import com.ydh.phonebook.model.BodyRegistration
import com.ydh.phonebook.model.ErrorResponseModel
import com.ydh.phonebook.model.ResponseModel
import com.ydh.phonebook.model.UserModel
import com.ydh.phonebook.repository.UserRemoteRepository
import com.ydh.phonebook.service.UserService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterPresenter (private val view: RegisterContract.View, private val repository: UserRemoteRepository): RegisterContract.Presenter{
    private val prefs: UserSession by lazy {
        UserSession(App.instance)
    }

    override fun userRegister(registerBody: BodyRegistration) {

        repository.userRegister(registerBody).enqueue(object : Callback<ResponseModel<String>> {
            override fun onResponse(call: Call<ResponseModel<String>>, response: Response<ResponseModel<String>>) {
                if (response.isSuccessful) {
                    if (response.body()!!.status) {
//                        val user = response.body()!!.data

                        prefs.email = registerBody.email
                        prefs.name = registerBody.name
                        prefs.password = registerBody.password
                        prefs.token = "Bearer ${response.body()!!.data}"
                        prefs.loggedIn = true


                        view.onSuccessRegister(response.body()!!.message)
                    }

                } else {
                    if (response.errorBody() != null) {
                        val gson = Gson()
                        val message: ErrorResponseModel = gson.fromJson(response.errorBody()!!.charStream(), ErrorResponseModel::class.java)

                        view.onFailedRegister(message.data)
                    }
                }

            }

            override fun onFailure(call: Call<ResponseModel<String>>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

}