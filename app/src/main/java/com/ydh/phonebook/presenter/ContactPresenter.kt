package com.ydh.phonebook.presenter

import com.google.gson.Gson
import com.ydh.phonebook.App
import com.ydh.phonebook.UserSession
import com.ydh.phonebook.contract.ContactContract
import com.ydh.phonebook.model.BodyAddContact
import com.ydh.phonebook.model.ContactModel
import com.ydh.phonebook.model.ErrorResponseModel
import com.ydh.phonebook.model.ResponseModel
import com.ydh.phonebook.repository.ContactRemoteRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ContactPresenter(private val view: ContactContract.View, private val repository: ContactRemoteRepository): ContactContract.Presenter {

    private val prefs: UserSession by lazy {
        UserSession(App.instance)
    }

    override fun getAllContact() {
        repository.getAllContact(prefs.token!!).enqueue(object : Callback<ResponseModel<List<ContactModel>>> {
            override fun onResponse(
                call: Call<ResponseModel<List<ContactModel>>>,
                response: Response<ResponseModel<List<ContactModel>>>
            ) {

                if (response.code() == 200){
                    view.onSuccessGetContact(response.body()!!.data)
                }else {
                    if (response.errorBody() != null) {
                        val gson = Gson()
                        val message: ErrorResponseModel = gson.fromJson(response.errorBody()!!.charStream(), ErrorResponseModel::class.java)

                        view.onFailed(message.data)
                    }
                }

            }

            override fun onFailure(call: Call<ResponseModel<List<ContactModel>>>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

    override fun deleteContact(contactModel: ContactModel, position: Int) {
        repository.deleteContact(prefs.token!!, contactModel.id).enqueue(object : Callback<ResponseModel<String>> {
            override fun onResponse(
                    call: Call<ResponseModel<String>>,
                    response: Response<ResponseModel<String>>
            ) {

                if (response.code() == 200){
                    view.onSuccessDeleteContact(response.body()!!.data, position)
                }else {
                    if (response.errorBody() != null) {
                        val gson = Gson()
                        val message: ErrorResponseModel = gson.fromJson(response.errorBody()!!.charStream(), ErrorResponseModel::class.java)

                        view.onFailed(message.data)
                    }
                }

            }

            override fun onFailure(call: Call<ResponseModel<String>>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

    override fun addContact(bodyAddContact: BodyAddContact) {
        repository.addContact(prefs.token!!, bodyAddContact).enqueue(object : Callback<ResponseModel<ContactModel>> {
            override fun onResponse(
                call: Call<ResponseModel<ContactModel>>,
                response: Response<ResponseModel<ContactModel>>
            ) {

                if (response.code() == 200){
                    view.onSuccessAddContact(response.body()!!.message, response.body()!!.data)
                }else {
                    if (response.errorBody() != null) {
                        val gson = Gson()
                        val message: ErrorResponseModel = gson.fromJson(response.errorBody()!!.charStream(), ErrorResponseModel::class.java)

                        view.onFailed(message.data)
                    }
                }

            }

            override fun onFailure(call: Call<ResponseModel<ContactModel>>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

    override fun updateContact(id: Int, bodyAddContact: BodyAddContact) {
        repository.updateContact(prefs.token!!,id, bodyAddContact).enqueue(object : Callback<ResponseModel<ContactModel>> {
            override fun onResponse(
                call: Call<ResponseModel<ContactModel>>,
                response: Response<ResponseModel<ContactModel>>
            ) {

                if (response.code() == 200){
                    view.onSuccessUpdateContact(response.body()!!.message, response.body()!!.data)
                }else {
                    if (response.errorBody() != null) {
                        val gson = Gson()
                        val message: ErrorResponseModel = gson.fromJson(response.errorBody()!!.charStream(), ErrorResponseModel::class.java)

                        view.onFailed(message.data)
                    }
                }

            }

            override fun onFailure(call: Call<ResponseModel<ContactModel>>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })    }
}