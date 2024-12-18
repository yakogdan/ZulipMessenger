package com.bogdankostyrko.messenger.presentation.adapters.users

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.bogdankostyrko.messenger.R
import com.bogdankostyrko.messenger.databinding.UserItemBinding
import com.bogdankostyrko.messenger.domain.models.Status
import com.bogdankostyrko.messenger.domain.models.UserModel
import com.bogdankostyrko.messenger.presentation.adapters.users.UsersAdapter.UserViewHolder

class UsersAdapter(
    private val onItemClick: (user: UserModel) -> Unit,
) : ListAdapter<UserModel, UserViewHolder>(UserAdapterItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            UserItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onItemClick,
        )
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class UserViewHolder(
        private val binding: UserItemBinding,
        private val onUserClick: (user: UserModel) -> Unit,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(user: UserModel) {
            binding.apply {
                tvName.text = user.name
                tvEmail.text = user.email
                val image = user.avatarUrl ?: R.drawable.avatar
                ivUserPhoto.load(image)
                ivStatus.visibility = when (user.status) {
                    Status.Active -> View.VISIBLE
                    Status.Offline -> View.GONE
                }
            }

            binding.root.setOnClickListener {
                onUserClick(user)
            }
        }
    }
}