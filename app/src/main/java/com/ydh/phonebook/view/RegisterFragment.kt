package com.ydh.phonebook.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.ydh.phonebook.Api
import com.ydh.phonebook.R
import com.ydh.phonebook.contract.RegisterContract
import com.ydh.phonebook.databinding.FragmentLoginBinding
import com.ydh.phonebook.databinding.FragmentRegisterBinding
import com.ydh.phonebook.model.BodyRegistration
import com.ydh.phonebook.presenter.LoginPresenter
import com.ydh.phonebook.presenter.RegisterPresenter
import com.ydh.phonebook.repository.UserRemoteRepository
import com.ydh.phonebook.service.UserService

class RegisterFragment : Fragment(), RegisterContract.View{

    lateinit var binding : FragmentRegisterBinding

    private val service: UserService by lazy { Api.userService }
    private val repositoryUser: UserRemoteRepository by lazy { UserRemoteRepository(service) }
    private val presenter: RegisterPresenter by lazy { RegisterPresenter(this, repositoryUser) }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        setView()

        return binding.root
    }

    private fun setView(){
        binding.apply{
            btRegRegister.setOnClickListener {
                presenter.userRegister(BodyRegistration(
                        etRegisterUserName.text.toString(),
                        etRegisterUserEmail.text.toString(),
                        etRegisterUserPassword.text.toString(),
                ))
            }
        }
    }

    override fun onSuccessRegister(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
        findNavController().navigate(R.id.homeFragment)
    }

    override fun onFailedRegister(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    override fun loading() {
        TODO("Not yet implemented")
    }


}