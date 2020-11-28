package com.ydh.phonebook.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.ydh.phonebook.R
import com.ydh.phonebook.databinding.FragmentContactDetailBinding
import com.ydh.phonebook.databinding.FragmentContactListBinding
import com.ydh.phonebook.model.ContactModel


class ContactDetailFragment : Fragment() {
    lateinit var binding : FragmentContactDetailBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentContactDetailBinding.inflate(inflater, container, false)




        setView()

        return binding.root
    }

    private fun setView(){
        binding.apply {

            arguments?.let {
                val args = ContactDetailFragmentArgs.fromBundle(it)
                binding.contact = args.contact
            }
        }
    }

    companion object{
        @JvmStatic
        @BindingAdapter("contactImage")
        fun loadImage(view: ImageView, url: String?){
            var imageUrl = url
            if (imageUrl.isNullOrEmpty()){
                imageUrl = "https://user-images.githubusercontent.com/24848110/33519396-7e56363c-d79d-11e7-969b-09782f5ccbab.png"
            }
            Glide.with(view.context)
                .load(imageUrl)
                .into(view)
        }
    }



}