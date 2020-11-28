package com.ydh.phonebook.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.ydh.phonebook.MainActivity
import com.ydh.phonebook.R
import com.ydh.phonebook.contract.ProfileContract
import com.ydh.phonebook.databinding.FragmentLoginBinding
import com.ydh.phonebook.databinding.FragmentProfileBinding
import com.ydh.phonebook.presenter.ContactPresenter
import com.ydh.phonebook.presenter.ProfilePresenter

class ProfileFragment : Fragment(), ProfileContract.View {

    lateinit var binding: FragmentProfileBinding
    private val presenter: ProfilePresenter by lazy { ProfilePresenter(this) }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)

        binding.btLogout.setOnClickListener {
            presenter.userLogout()
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        presenter.getUserInfo()
    }

    override fun onSuccessLogout(message: String) {
        activity?.startActivity(Intent(activity,MainActivity::class.java))
        Toast.makeText(context, "Successful logout", Toast.LENGTH_LONG).show()
    }

    override fun onSuccessUser(name: String, email: String) {
        binding.apply {
            tvUserEmail.text = email
            tvUserName.text = name
        }
    }

}