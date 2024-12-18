package com.bogdankostyrko.messenger.presentation.screens.chat.customviews

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.bogdankostyrko.messenger.R
import com.bogdankostyrko.messenger.tools.convertSpToPixels

class ReactionView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyle: Int = 0,
    defTheme: Int = 0,
) : View(context, attributeSet, defStyle, defTheme) {

    private var emojiForDrawing: String = ""
    private var countForDrawing: Int = 0

    private val textPaint = TextPaint(Paint.ANTI_ALIAS_FLAG).apply {
        color = ContextCompat.getColor(context, R.color.light_grey)
        textSize = 14.convertSpToPixels(context).toFloat()
    }

    private val textRect = Rect()

    private val reactionPadding =
        context.resources.getDimension(R.dimen.reaction_view_padding).toInt()

    init {
        setPadding(
            reactionPadding,
            reactionPadding,
            reactionPadding,
            reactionPadding
        )
        setBackgroundResource(R.drawable.emoji_bg)
        layoutParams = ViewGroup.MarginLayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        isSelected = false
    }

    fun setValue(
        emoji: String,
        count: Int,
        isSelected: Boolean,
    ) {
        emojiForDrawing = emoji
        countForDrawing = count
        this.isSelected = isSelected
        requestLayout()
    }

    private fun getReactionForDrawing() = "$emojiForDrawing $countForDrawing"

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val reactionForDrawing = getReactionForDrawing()
        textPaint.getTextBounds(
            reactionForDrawing,
            0,
            reactionForDrawing.length,
            textRect
        )

        val actualWidth =
            resolveSize(paddingRight + paddingLeft + textRect.width(), widthMeasureSpec)

        val actualHeight =
            resolveSize(paddingTop + paddingBottom + textRect.height(), heightMeasureSpec)

        setMeasuredDimension(
            actualWidth,
            actualHeight
        )
    }

    override fun onDraw(canvas: Canvas) {
        val topOffset = height / 2 - textRect.exactCenterY()
        canvas.drawText(getReactionForDrawing(), paddingLeft.toFloat(), topOffset, textPaint)
    }
}