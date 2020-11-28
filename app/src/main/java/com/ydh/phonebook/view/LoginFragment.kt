package com.ydh.phonebook.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.ydh.phonebook.Api
import com.ydh.phonebook.contract.LoginContract
import com.ydh.phonebook.R
import com.ydh.phonebook.databinding.FragmentLoginBinding
import com.ydh.phonebook.model.ContactModel
import com.ydh.phonebook.model.LoginBody
import com.ydh.phonebook.model.UserModel
import com.ydh.phonebook.presenter.LoginPresenter
import com.ydh.phonebook.repository.UserRemoteRepository
import com.ydh.phonebook.service.UserService


class LoginFragment : Fragment(), LoginContract.View {
    lateinit var binding : FragmentLoginBinding

    private val service: UserService by lazy { Api.userService }
    private val repositoryUser: UserRemoteRepository by lazy { UserRemoteRepository(service) }
    private val presenter: LoginPresenter by lazy { LoginPresenter(this, repositoryUser) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        setView()
        return binding.root
    }

    private fun setView(){
        binding.apply {
            btLogLogin.setOnClickListener {
                presenter.userLogin(LoginBody(etLoginUserEmail.text.toString(), etLoginUserPassword.text.toString()))
            }
        }
    }
    override fun onSuccessLogin(userModel: UserModel, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
        findNavController().navigate(R.id.homeFragment)

    }

    override fun onFailedLogin(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }


}