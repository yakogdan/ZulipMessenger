package com.bogdankostyrko.messenger.presentation.adapters.delegate

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder

interface AdapterDelegate {

    fun onCreateViewHolder(parent: ViewGroup): ViewHolder

    fun onBindViewHolder(holder: ViewHolder, delegateItem: DelegateItem, position: Int)

    fun isOfViewType(delegateItem: DelegateItem): Boolean
}