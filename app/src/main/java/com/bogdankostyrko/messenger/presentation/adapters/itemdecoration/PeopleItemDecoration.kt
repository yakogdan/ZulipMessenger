package com.bogdankostyrko.messenger.presentation.adapters.itemdecoration

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bogdankostyrko.messenger.tools.convertDpToPixels

class PeopleItemDecoration(
    private var firstTop: Int,
    private var bottom: Int,
    private val left: Int,
    private val right: Int,
    private val context: Context,
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State,
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        outRect.apply {
            if (parent.getChildAdapterPosition(view) == 0) {
                top = this@PeopleItemDecoration.firstTop.convertDpToPixels(context)
            }
            bottom = this@PeopleItemDecoration.bottom.convertDpToPixels(context)
            left = this@PeopleItemDecoration.left.convertDpToPixels(context)
            right = this@PeopleItemDecoration.right.convertDpToPixels(context)
        }
    }
}