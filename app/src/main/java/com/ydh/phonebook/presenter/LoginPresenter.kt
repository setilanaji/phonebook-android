package com.ydh.phonebook.presenter

import com.google.gson.Gson
import com.ydh.phonebook.App
import com.ydh.phonebook.UserSession
import com.ydh.phonebook.contract.LoginContract
import com.ydh.phonebook.model.ErrorResponseModel
import com.ydh.phonebook.model.LoginBody
import com.ydh.phonebook.model.ResponseModel
import com.ydh.phonebook.model.UserModel
import com.ydh.phonebook.repository.UserRemoteRepository
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginPresenter(private val view: LoginContract.View, private val repositoryUser: UserRemoteRepository):
    LoginContract.Presenter {

    private val prefs: UserSession by lazy {
        UserSession(App.instance)
    }

    override fun userLogin(loginBody: LoginBody) {
        repositoryUser.userLogin(loginBody).enqueue(object : Callback<ResponseModel<UserModel>> {
            override fun onResponse(
                    call: Call<ResponseModel<UserModel>>,
                    response: Response<ResponseModel<UserModel>>
            ) {
                if (response.isSuccessful) {
                    if (response.body()!!.status) {
                        val user = response.body()!!.data

                        prefs.email = user.email
                        prefs.name = user.name
                        prefs.password = user.password
                        prefs.token = "Bearer ${user.token}"

                        prefs.loggedIn = true

                        view.onSuccessLogin( response.body()!!.message)
                    }

                } else {
                    if (response.errorBody() != null) {
                        val gson = Gson()
                        val message: ErrorResponseModel = gson.fromJson(response.errorBody()!!.charStream(), ErrorResponseModel::class.java)

                        view.onFailedLogin(message.data)
                    }
                }

            }

            override fun onFailure(call: Call<ResponseModel<UserModel>>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })

    }


}