package com.ydh.phonebook.contract

import com.ydh.phonebook.model.BodyAddContact
import com.ydh.phonebook.model.ContactModel
import com.ydh.phonebook.model.LoginBody
import java.text.FieldPosition

interface ContactContract {

    interface View{
        fun onSuccessGetContact(list: List<ContactModel>)
        fun onFailed(message: String)
        fun onSuccessDeleteContact(message: String, id: Int)
        fun onSuccessAddContact(message: String, contactModel: ContactModel)
        fun onSuccessUpdateContact(message: String, contactModel: ContactModel)

        fun loading()

    }

    interface Presenter{
        fun getAllContact()
        fun deleteContact(contactModel: ContactModel, position: Int)
        fun addContact(bodyAddContact: BodyAddContact)
        fun updateContact(id: Int, bodyAddContact: BodyAddContact)
    }

}