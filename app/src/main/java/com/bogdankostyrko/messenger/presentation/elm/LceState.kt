package com.bogdankostyrko.messenger.presentation.elm

sealed interface LceState<out R> {

    data class Content<out T>(val content: T) : LceState<T>

    data object Loading : LceState<Nothing>

    data object Error : LceState<Nothing>
}