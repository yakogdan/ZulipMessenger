package com.bogdankostyrko.messenger.presentation.adapters.itemdecoration

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bogdankostyrko.messenger.tools.convertDpToPixels

class ChatItemDecoration(
    private val padding: Int,
    private val context: Context,
) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State,
    ) {
        if (parent.getChildAdapterPosition(view) == 0) {
            outRect.bottom = padding.convertDpToPixels(context)
        }

        outRect.top = padding
    }
}