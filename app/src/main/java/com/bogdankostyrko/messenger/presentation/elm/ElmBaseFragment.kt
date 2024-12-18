package com.bogdankostyrko.messenger.presentation.elm

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import vivid.money.elmslie.android.renderer.ElmRendererDelegate
import vivid.money.elmslie.core.store.Store

abstract class ElmBaseFragment<
        Effect : Any,
        ViewState : Any,
        Event : Any,
        >(@LayoutRes layoutId: Int) :
    Fragment(layoutId), ElmRendererDelegate<Effect, ViewState> {

    abstract val store: Store<Event, Effect, ViewState>
}