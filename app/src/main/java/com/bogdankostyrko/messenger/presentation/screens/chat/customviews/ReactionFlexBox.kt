package com.bogdankostyrko.messenger.presentation.screens.chat.customviews

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.view.marginLeft
import com.bogdankostyrko.messenger.R
import com.bogdankostyrko.messenger.domain.models.MessageModel

class ReactionFlexBox @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyle: Int = 0,
    defTheme: Int = 0,
) : ViewGroup(context, attributeSet, defStyle, defTheme) {

    private val children = mutableListOf<View>()

    private val childrenPadding =
        context.resources.getDimension(R.dimen.reaction_view_padding).toInt()

    private var rowList = mutableListOf<List<View>>()
    private var rowHeights = mutableListOf<Int>()

    private var isMyMessage = false

    private fun addChild(child: View) {
        children.add(0, child)
        addView(child)
        requestLayout()
    }

    private fun cleanChildren() {
        removeAllViews()
        children.clear()
        requestLayout()
    }

    fun setMyMessage(isMyMessage: Boolean) {
        this.isMyMessage = isMyMessage
        requestLayout()
    }

    fun updateReactions(
        message: MessageModel,
        position: Int,
        onEmojiClick: (
            emojiName: String,
            messageId: Int,
        ) -> Unit,
        onAddEmojiClick: (messageId: Int, position: Int) -> Unit,
    ) {
        cleanChildren()
        isVisible = message.reactions.isNotEmpty()

        if (message.reactions.isNotEmpty()) {
            showAddingReactionButton(
                message = message,
                position = position,
                onAddEmojiClick = onAddEmojiClick,
            )
        }

        message.reactions
            .groupBy { it.emojiCode }
            .forEach { mapEntry ->
                val reaction = ReactionView(context)
                val isClicked = mapEntry.value.any { it.isMyReaction }

                reaction.setValue(
                    emoji = mapEntry.key,
                    count = mapEntry.value.size,
                    isSelected = isClicked,
                )

                reaction.setOnClickListener {
                    onEmojiClick(
                        /*emoji = */ mapEntry.value.first().emojiName,
                        /*messageId = */ message.id,
                    )
                }

                addChild(reaction)
            }

    }

    private fun showAddingReactionButton(
        message: MessageModel,
        position: Int,
        onAddEmojiClick: (messageId: Int, position: Int) -> Unit,
    ) {
        val add = AddReactionView(context)

        add.setOnClickListener {
            onAddEmojiClick(message.id, position)
        }

        addChild(add)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {

        val parentWidth = MeasureSpec.getSize(widthMeasureSpec)
        val parentHeight = MeasureSpec.getSize(heightMeasureSpec)

        var maxHeight = 0
        var currentWidth = 0
        var currentRow = mutableListOf<View>()

        rowList.clear()
        rowHeights.clear()

        children.forEach { child ->
            measureChild(child, widthMeasureSpec, heightMeasureSpec)

            if (currentWidth + child.measuredWidth > parentWidth) {

                rowList.add(currentRow)
                rowHeights.add(maxHeight)

                currentRow = mutableListOf()
                currentWidth = 0
                maxHeight = 0
            }

            currentRow.add(child)
            currentWidth += child.measuredWidth + childrenPadding
            maxHeight = maxOf(maxHeight, child.measuredHeight)
        }

        if (currentRow.isNotEmpty()) {
            rowList.add(currentRow)
            rowHeights.add(maxHeight)
        }

        var totalHeight = paddingTop + paddingBottom

        for (height in rowHeights) {
            totalHeight += height + childrenPadding
        }

        setMeasuredDimension(parentWidth, minOf(totalHeight, parentHeight))
    }

    override fun onLayout(p0: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        var currentTop = paddingTop
        var currentLeft = if (isMyMessage) right - marginLeft - childrenPadding else paddingLeft
        var rowWidthMax = 0

        for (i in rowList.indices) {
            val currentRow = rowList[i]
            val rowHeight = rowHeights[i]

            val rowWidth =
                currentRow.sumOf { it.measuredWidth } + (currentRow.size - 1) * childrenPadding

            if (rowWidth > rowWidthMax) {
                rowWidthMax = rowWidth
            }

            if (isMyMessage && (right - marginLeft - childrenPadding - rowWidthMax < currentLeft)) {
                currentLeft = right - marginLeft - childrenPadding - rowWidthMax
            }

            for (j in currentRow.indices) {
                val child = currentRow[j]
                val childWidth = child.measuredWidth
                val childHeight = child.measuredHeight
                val childLeft = currentLeft
                val childTop = currentTop
                val childRight = currentLeft + childWidth
                val childBottom = currentTop + childHeight

                child.layout(childLeft, childTop, childRight, childBottom)
                currentLeft += childWidth + childrenPadding
            }

            currentTop += rowHeight + childrenPadding
            currentLeft = if (isMyMessage) right - marginLeft - childrenPadding else paddingLeft
        }
    }
}