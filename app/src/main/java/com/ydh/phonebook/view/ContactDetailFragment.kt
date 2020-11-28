package com.ydh.phonebook.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ydh.phonebook.R


class ContactDetailFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        arguments?.let {
            val args = ContactDetailFragmentArgs.fromBundle(it)
            val contact = args.contact
            println("Safe Argument Received=${contact}")
        }
        return inflater.inflate(R.layout.fragment_contact_detail, container, false)

    }

}