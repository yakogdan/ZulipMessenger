package com.bogdankostyrko.messenger.presentation.adapters.delegate

interface DelegateItem {

    fun content(): Any

    fun id(): Int

    fun compareToOther(other: DelegateItem): Boolean
}