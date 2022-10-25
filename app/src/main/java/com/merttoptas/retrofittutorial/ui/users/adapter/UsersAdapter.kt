package com.merttoptas.retrofittutorial.ui.users.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.merttoptas.retrofittutorial.data.model.UsersDTO
import com.merttoptas.retrofittutorial.databinding.ItemUserLayoutBinding
import com.merttoptas.retrofittutorial.ui.users.UserFragment

class UsersAdapter(private val listener: UserFragment) : ListAdapter<UsersDTO, UsersAdapter.UserViewHolder>(UsersDiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            ItemUserLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(getItem(position), listener)
    }

    class UserViewHolder(private val binding: ItemUserLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(name: UsersDTO, listener: UserFragment) {
            binding.dataHolder = name
            binding.executePendingBindings()
        }
    }

    class UsersDiffUtil : DiffUtil.ItemCallback<UsersDTO>() {
        override fun areItemsTheSame(oldItem: UsersDTO, newItem: UsersDTO): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: UsersDTO, newItem: UsersDTO): Boolean {
            return oldItem == newItem
        }
    }
}

interface OnUserClickListener {
    fun onUserClick(name: UsersDTO)
}