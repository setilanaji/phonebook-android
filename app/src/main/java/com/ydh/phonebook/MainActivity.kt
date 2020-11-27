package com.ydh.phonebook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.ydh.phonebook.model.ContactModel
import com.ydh.phonebook.model.LoginBody
import com.ydh.phonebook.repository.ContactRemoteRepository
import com.ydh.phonebook.repository.UserRemoteRepository
import com.ydh.phonebook.service.ContactService
import com.ydh.phonebook.service.UserService

class MainActivity : AppCompatActivity(), LoginContract.View {

    private val service: UserService by lazy { Api.userService }
    private val contactService: ContactService by lazy { Api.contactService }
    private val repositoryUser: UserRemoteRepository by lazy { UserRemoteRepository(service) }
    private val repositoryContact: ContactRemoteRepository by lazy { ContactRemoteRepository(contactService) }
    private val presenter: LoginPresenter by lazy { LoginPresenter(this, repositoryUser, repositoryContact) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter.userLogin(LoginBody("l200140004@gmail.com", "l200140004"))
        presenter.getAllContact()


    }

    override fun onSuccessLogin(token: String) {
        Toast.makeText(this, "Check $token", Toast.LENGTH_LONG).show()
    }

    override fun onSuccessGetContact(list: List<ContactModel>) {
        println("Test out $list")
    }
}