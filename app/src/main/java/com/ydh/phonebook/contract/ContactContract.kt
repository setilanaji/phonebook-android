package com.ydh.phonebook.contract

import com.ydh.phonebook.model.ContactModel
import com.ydh.phonebook.model.LoginBody
import java.text.FieldPosition

interface ContactContract {

    interface View{
        fun onSuccessGetContact(list: List<ContactModel>)
        fun onFailed(message: String)
        fun onSuccessDeleteContact(message: String, id: Int)

        fun loading()

    }

    interface Presenter{
        fun getAllContact()
        fun deleteContact(contactModel: ContactModel, position: Int)
    }

}