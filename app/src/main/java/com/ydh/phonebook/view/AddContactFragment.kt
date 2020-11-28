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
import com.ydh.phonebook.contract.ContactContract
import com.ydh.phonebook.databinding.FragmentAddContactBinding
import com.ydh.phonebook.model.BodyAddContact
import com.ydh.phonebook.model.ContactModel
import com.ydh.phonebook.presenter.ContactPresenter
import com.ydh.phonebook.repository.ContactRemoteRepository
import com.ydh.phonebook.service.ContactService


class AddContactFragment : Fragment(), ContactContract.View {

    lateinit var binding: FragmentAddContactBinding

    private val service: ContactService by lazy { Api.contactService }
    private val repositoryContact: ContactRemoteRepository by lazy { ContactRemoteRepository(service) }
    private val presenter: ContactPresenter by lazy { ContactPresenter(this, repositoryContact) }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddContactBinding.inflate(inflater, container, false)

        setView()
        return binding.root
    }

    private fun setView(){

        binding.run {

            arguments?.let {
                val args = ContactDetailFragmentArgs.fromBundle(it)
                    binding.contact = args.contact
                    binding.btAddContact.text = args.status
            }
            arguments?.let {
                val args = ContactListFragmentArgs.fromBundle(it)
                binding.contact = args.contact
                binding.btAddContact.text = args.status
            }

            btAddContact.setOnClickListener {
                if (binding.btAddContact.text == "ADD"){
                    presenter.addContact(
                        BodyAddContact(
                            etAddContactName.text.toString(),
                            etAddContactPhone.text.toString(),
                            etAddContactJob.text.toString(),
                            etAddContactCompany.text.toString(),
                            etAddContactEmail.text.toString()
                        )
                    )
                }else{
                    presenter.updateContact(
                        binding.contact!!.id,
                        BodyAddContact(
                            etAddContactName.text.toString(),
                            etAddContactPhone.text.toString(),
                            etAddContactJob.text.toString(),
                            etAddContactCompany.text.toString(),
                            etAddContactEmail.text.toString()
                        )
                    )
                }

            }
        }


    }

    private fun cleanForm(){
        binding.apply {
            etAddContactName.setText("")
            etAddContactPhone.setText("")
            etAddContactJob.setText("")
            etAddContactCompany.setText("")
            etAddContactEmail.setText("")
        }
    }

    override fun onSuccessGetContact(list: List<ContactModel>) {
        TODO("Not yet implemented")
    }



    override fun onFailed(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG ).show()
    }

    override fun onSuccessDeleteContact(message: String, id: Int) {
        TODO("Not yet implemented")
    }

    override fun onSuccessAddContact(message: String, contactModel: ContactModel) {
        Toast.makeText(context, message, Toast.LENGTH_LONG ).show()
        cleanForm()
    }

    override fun onSuccessUpdateContact(message: String, contactModel: ContactModel) {
        Toast.makeText(context, message, Toast.LENGTH_LONG ).show()
//        val action = AddC.actionContactDetailFragmentToAddContactFragment(binding.contact, status = "DONE")
        findNavController().navigateUp()
    }

    override fun loading() {
        TODO("Not yet implemented")
    }


}