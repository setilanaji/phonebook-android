package com.ydh.phonebook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.ydh.phonebook.contract.LoginContract
import com.ydh.phonebook.databinding.ActivityMainBinding
import com.ydh.phonebook.model.ContactModel
import com.ydh.phonebook.model.LoginBody
import com.ydh.phonebook.model.UserModel
import com.ydh.phonebook.presenter.LoginPresenter
import com.ydh.phonebook.repository.ContactRemoteRepository
import com.ydh.phonebook.repository.UserRemoteRepository
import com.ydh.phonebook.service.ContactService
import com.ydh.phonebook.service.UserService

class MainActivity : AppCompatActivity() {

    private lateinit var navigationController: NavController
    lateinit var binding: ActivityMainBinding

    private val prefs: UserSession by lazy {
        UserSession(App.instance)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigationController =  this.findNavController(R.id.navHostFragment)

        if (prefs.loggedIn){
            navigationController.navigate(R.id.homeFragment)
        }else{
            navigationController.navigate(R.id.loginFragment)
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        return super.onSupportNavigateUp()
    }


}