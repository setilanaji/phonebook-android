package com.ydh.phonebook.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ydh.phonebook.R
import com.ydh.phonebook.databinding.ItemContactBinding
import com.ydh.phonebook.databinding.ItemContactCategoryTitleBinding
import com.ydh.phonebook.model.ContactModel
import java.util.*

sealed class Contact{
    data class Category(val category: String):Contact()
    data class Data(val contact: ContactModel): Contact()
}

class ContactAdapter(
        private val context: Context, private val listener: ContactListerner
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var contactList = mutableListOf<Contact>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
//        val inflater = LayoutInflater.from(context)
//        val binding: ItemContactBinding = DataBindingUtil.inflate(inflater,
//                R.layout.item_contact,parent,false)
//        return MyViewHolder(binding, listener)

        return when(viewType){
            TYPE_HEADER -> HeaderViewHolder(
                    ItemContactCategoryTitleBinding.inflate(LayoutInflater.from(context), parent, false)
            )
            TYPE_DATA -> MyViewHolder(
                    ItemContactBinding.inflate(LayoutInflater.from(context), parent, false),
                    listener
            )
            else -> throw IllegalArgumentException("Unsupported view type")
        }


    }


    companion object {
        private const val TYPE_HEADER = 0
        private const val TYPE_DATA = 1
    }

    override fun getItemViewType(position: Int): Int {
        return when(contactList[position]){
            is Contact.Category -> TYPE_HEADER
            is Contact.Data -> TYPE_DATA
        }

    }

    fun generateContact(it: List<ContactModel>){
        val list = mutableListOf<Contact>()
        val sortedList = it.sortedBy { it.name }
        var temp = ""

        sortedList.forEach { model ->
            if (temp != model.name[0].toUpperCase().toString()) {
                temp = model.name[0].toUpperCase().toString()
                list.add(Contact.Category(temp))
            }
            list.add(Contact.Data(model))
        }
        this.setData(list)
    }

    fun setData(item: MutableList<Contact>){
        this.contactList = item
        notifyDataSetChanged()
    }

    fun deleteContact(position: Int) {
//
            contactList.removeAt(position)
            notifyItemRemoved(position)

    }

    fun updateContact(contactModel: Contact) {
//        val index = contactList.indexOfFirst { it.id == contactModel.id }
//        if (index != -1) {
//            contactList[index] = contactModel
//            notifyItemChanged(index)
//        }
    }

    fun getData(position: Int): Contact{
        return contactList[position]
    }

    fun addContact(todoModel: Contact) {
        contactList.add(0, todoModel)
        notifyItemInserted(0)
    }

    interface ContactListerner {
        fun onClick(todoModel: ContactModel)
        fun onDelete(id: Long)
        fun onChange(todoModel: ContactModel)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
//        holder.itemBinding.user = contactList[position]


        val contact = contactList[position]

        if (contact is Contact.Data && holder is MyViewHolder) {
            holder.itemBinding.user = contact.contact
        } else if (contact is Contact.Category && holder is HeaderViewHolder) {
            holder.bindData(contact.category)
        }
    }

    override fun getItemCount(): Int {
        return contactList.size
    }

    inner class HeaderViewHolder(
            private val binding: ItemContactCategoryTitleBinding
    ): RecyclerView.ViewHolder(binding.root){
        fun bindData(category: String){
            binding.run {
                tvCategory.text = category.toUpperCase(Locale.getDefault())
            }

        }
    }

    class MyViewHolder(val itemBinding: ItemContactBinding,
                       private val listener: ContactListerner
    ) : RecyclerView.ViewHolder(itemBinding.root){

        private var binding : ItemContactBinding? = null

        init {
            if (itemBinding.user?.image == null){
                itemBinding.user?.image == "https://user-images.githubusercontent.com/24848110/33519396-7e56363c-d79d-11e7-969b-09782f5ccbab.png"
            }

            this.binding = itemBinding

//            itemBinding.ivFavTodo.setOnClickListener{
//                if (itemBinding.ivFavTodo.isChecked){
//                    listener.onFavClick(itemBinding.todo!!)
//                }else{
//                    listener.onDelFavClick(itemBinding.todo!!)
//                }
//            }
//
//            itemBinding.ivStatus.setOnClickListener{
//                val item = itemBinding.todo
//                if (itemBinding.ivStatus.isChecked){
//                    item!!.status = true
//                    listener.onChange(item)
//                }else{
//                    item!!.status = false
//                    listener.onChange(item)
//                }
//            }
        }

        companion object{
            @JvmStatic
            @BindingAdapter("userImage")
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

}