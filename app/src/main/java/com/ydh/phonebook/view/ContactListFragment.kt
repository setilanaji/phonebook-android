package com.ydh.phonebook.view

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.ydh.phonebook.Api
import com.ydh.phonebook.R
import com.ydh.phonebook.contract.ContactContract
import com.ydh.phonebook.databinding.FragmentContactListBinding
import com.ydh.phonebook.databinding.FragmentLoginBinding
import com.ydh.phonebook.model.ContactModel
import com.ydh.phonebook.presenter.ContactPresenter
import com.ydh.phonebook.presenter.LoginPresenter
import com.ydh.phonebook.repository.ContactRemoteRepository
import com.ydh.phonebook.repository.UserRemoteRepository
import com.ydh.phonebook.service.ContactService
import com.ydh.phonebook.service.UserService
import com.ydh.phonebook.util.SwipeToDelete
import com.ydh.phonebook.view.adapter.Contact
import com.ydh.phonebook.view.adapter.ContactAdapter


class ContactListFragment : Fragment(), ContactContract.View, ContactAdapter.ContactListerner {

    lateinit var binding : FragmentContactListBinding
    private val adapter by lazy { ContactAdapter(requireContext(), this) }

    private val service: ContactService by lazy { Api.contactService }
    private val repositoryContact: ContactRemoteRepository by lazy { ContactRemoteRepository(service) }
    private val presenter: ContactPresenter by lazy { ContactPresenter(this, repositoryContact) }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentContactListBinding.inflate(inflater, container, false)

        setView()
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_user, menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(
            item,
            requireView().findNavController()
        ) || super.onOptionsItemSelected(item)
    }

    private fun setView() {
        setHasOptionsMenu(true)

        binding.run {
            rvContactList.adapter = adapter
            val swipeHandler = object : SwipeToDelete(requireContext()) {
                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    val adapter = rvContactList.adapter as ContactAdapter
                    val pos = viewHolder.adapterPosition
                    if (adapter.getData(pos) is Contact.Data){
                        val item = adapter.getData(pos) as Contact.Data
                        presenter.deleteContact(item.contact, pos)
                    }else{
                        onFailed("You cant delete this")
                    }
                }
            }
            val itemTouchHelper = ItemTouchHelper(swipeHandler)
            itemTouchHelper.attachToRecyclerView(rvContactList)

            btAdd.setOnClickListener {
                val action = ContactListFragmentDirections.actionContactListFragmentToAddContactFragment(null, "ADD")
                findNavController().navigate(action)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        presenter.getAllContact()
    }

    override fun onSuccessGetContact(list: List<ContactModel>) {
        adapter.generateContact(list.toMutableList())
    }

    override fun onFailed(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG ).show()
    }

    override fun onSuccessDeleteContact(message: String, id: Int) {
        Toast.makeText(context, message, Toast.LENGTH_LONG ).show()
        adapter.deleteContact(id)
        onResume()
    }

    override fun onSuccessAddContact(message: String, contactModel: ContactModel) {
        TODO("Not yet implemented")
    }

    override fun onSuccessUpdateContact(message: String, contactModel: ContactModel) {
        TODO("Not yet implemented")
    }

    override fun loading() {
        TODO("Not yet implemented")
    }

    override fun onClick(contact: ContactModel) {
        val action = ContactListFragmentDirections.actionContactListFragmentToContactDetailFragment(contact, status = "ADD")
        findNavController().navigate(action)
    }

    override fun onDelete(id: Long) {
        TODO("Not yet implemented")
    }

    override fun onChange(todoModel: ContactModel) {
        TODO("Not yet implemented")
    }



}