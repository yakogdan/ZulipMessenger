package com.bogdankostyrko.messenger.presentation.adapters.users

import androidx.recyclerview.widget.DiffUtil
import com.bogdankostyrko.messenger.domain.models.UserModel

class UserAdapterItemCallback : DiffUtil.ItemCallback<UserModel>() {

    override fun areItemsTheSame(oldItem: UserModel, newItem: UserModel): Boolean =
        oldItem.userId == newItem.userId

    override fun areContentsTheSame(oldItem: UserModel, newItem: UserModel): Boolean =
        oldItem == newItem
}