package com.bogdankostyrko.messenger.presentation.screens.chat.customviews

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.core.view.marginBottom
import androidx.core.view.marginLeft
import androidx.core.view.marginRight
import androidx.core.view.marginTop
import coil.load
import com.bogdankostyrko.messenger.R
import com.bogdankostyrko.messenger.domain.models.MessageModel
import com.google.android.material.imageview.ShapeableImageView

class MessageViewGroup @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : ViewGroup(context, attrs, defStyleAttr, defStyleRes) {

    private val avatar: ShapeableImageView
    private val userName: TextView
    private var textMessage: TextView
    private var reactions: ReactionFlexBox

    private var isMyMessage: Boolean = false

    init {
        inflate(context, R.layout.message_view_group, this)
        avatar = findViewById(R.id.iv_message_avatar)
        userName = findViewById(R.id.tv_message_name)
        textMessage = findViewById(R.id.tv_message_text)
        reactions = findViewById(R.id.fb_reaction)
    }

    fun setMessage(
        messageModel: MessageModel,
        position: Int,

        onMessageLongClick: (
            messagePosition: Int,
            messageId: Int,
        ) -> Unit,

        onEmojiClick: (
            emojiName: String,
            messageId: Int,
        ) -> Unit,

        onAddEmojiClick: (
            messageId: Int,
            position: Int,
        ) -> Unit
    ) {
        this.isMyMessage = messageModel.isMyMessage
        setLayoutVisibility()
        reactions.setMyMessage(isMyMessage = isMyMessage)
        userName.text = messageModel.userName
        textMessage.text = messageModel.text
        avatar.load(messageModel.avatarUrl)

        reactions.updateReactions(
            message = messageModel,
            position = position,
            onEmojiClick = onEmojiClick,
            onAddEmojiClick = onAddEmojiClick,
        )

        textMessage.setOnLongClickListener {
            onMessageLongClick(
                /*messagePosition = */ position,
                /*messageId = */ messageModel.id
            )
            return@setOnLongClickListener true
        }

        requestLayout()
    }

    private fun setLayoutVisibility() {
        if (isMyMessage) {
            textMessage.background = ContextCompat.getDrawable(context, R.drawable.my_message_bg)
            avatar.isVisible = false
            userName.isVisible = false
        } else {
            userName.background = ContextCompat.getDrawable(context, R.drawable.message_bg)
            textMessage.background = ContextCompat.getDrawable(context, R.drawable.message_bg)
            avatar.isVisible = true
            userName.isVisible = true
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {

        val basicIndent = context.resources.getDimension(R.dimen.default_indent).toInt()
        val totalWidth: Int
        val totalHeight: Int

        if (isMyMessage) {
            setPadding(basicIndent, basicIndent, basicIndent, basicIndent)

            measureChildWithMargins(
                textMessage,
                widthMeasureSpec,
                0,
                heightMeasureSpec,
                0
            )

            measureChildWithMargins(
                reactions,
                widthMeasureSpec,
                0,
                heightMeasureSpec,
                0
            )

            totalWidth = paddingLeft + paddingRight +
                    textMessage.marginLeft + textMessage.marginRight +
                    maxOf(textMessage.measuredWidth, reactions.measuredWidth)

            totalHeight = paddingTop + paddingTop + paddingBottom +
                    textMessage.paddingBottom + textMessage.paddingTop +
                    textMessage.measuredHeight + reactions.measuredHeight

        } else {
            setPadding(0, basicIndent, 0, basicIndent)

            measureChildWithMargins(avatar, widthMeasureSpec, 0, heightMeasureSpec, 0)

            measureChildWithMargins(
                userName,
                widthMeasureSpec,
                avatar.measuredWidth + avatar.marginLeft + avatar.marginRight,
                heightMeasureSpec,
                avatar.measuredHeight + avatar.marginTop + avatar.marginBottom,
            )

            measureChildWithMargins(
                textMessage,
                widthMeasureSpec,
                avatar.measuredWidth + avatar.marginLeft + avatar.marginRight,
                heightMeasureSpec,
                avatar.measuredHeight + avatar.marginTop + avatar.marginBottom,
            )

            measureChildWithMargins(
                reactions,
                widthMeasureSpec,
                avatar.measuredWidth + avatar.marginLeft + avatar.marginRight,
                heightMeasureSpec,
                avatar.measuredHeight + avatar.marginTop + avatar.marginBottom,
            )

            totalWidth = paddingLeft + paddingRight +
                    avatar.measuredWidth + avatar.marginLeft + avatar.marginRight +
                    textMessage.marginLeft + textMessage.marginRight +
                    maxOf(
                        textMessage.measuredWidth,
                        userName.measuredWidth,
                        reactions.measuredWidth
                    )

            totalHeight = paddingTop + paddingBottom +
                    userName.paddingBottom + userName.paddingTop +
                    userName.measuredHeight - textMessage.paddingBottom -
                    textMessage.paddingBottom - textMessage.paddingTop +
                    textMessage.measuredHeight + reactions.measuredHeight
        }

        setMeasuredDimension(totalWidth, totalHeight)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {

        if (isMyMessage) {
            onLayoutMyMessage()
        } else {
            onLayoutMessage()
        }
    }

    private fun onLayoutMyMessage() {
        var offsetY = paddingTop

        textMessage.layout(
            width - textMessage.paddingRight - textMessage.measuredWidth,
            offsetY + textMessage.marginTop,
            width - paddingRight,
            offsetY + textMessage.marginTop + textMessage.measuredHeight +
                    paddingTop / 2 + paddingTop,
        )

        offsetY += paddingTop

        reactions.layout(
            width - paddingRight - reactions.measuredWidth,
            offsetY + reactions.marginTop + textMessage.marginTop +
                    textMessage.measuredHeight + paddingTop / 2,
            width - paddingRight,
            offsetY + textMessage.marginTop + textMessage.measuredHeight +
                    textMessage.paddingBottom + textMessage.paddingTop + reactions.measuredHeight +
                    paddingTop + paddingBottom + paddingTop / 2,
        )
    }

    private fun onLayoutMessage() {
        var offsetX = paddingLeft + avatar.marginLeft
        val offsetY = paddingTop

        avatar.layout(
            offsetX,
            offsetY + avatar.marginTop,
            offsetX + avatar.measuredWidth,
            offsetY + avatar.measuredHeight + avatar.marginTop,
        )

        offsetX += avatar.measuredWidth + avatar.marginRight

        userName.layout(
            offsetX,
            offsetY + userName.marginTop,
            offsetX + maxOf(textMessage.measuredWidth, userName.measuredWidth),
            offsetY + userName.marginTop + userName.measuredHeight,
        )

        textMessage.layout(
            offsetX,
            offsetY + textMessage.marginTop + userName.measuredHeight - userName.paddingBottom,
            offsetX + maxOf(textMessage.measuredWidth, userName.measuredWidth),
            offsetY + textMessage.marginTop + textMessage.measuredHeight + userName.measuredHeight,
        )

        reactions.layout(
            offsetX,
            offsetY + reactions.marginTop + textMessage.marginTop +
                    userName.measuredHeight + textMessage.measuredHeight,
            width,
            offsetY + textMessage.marginTop + textMessage.measuredHeight +
                    textMessage.paddingBottom + textMessage.paddingTop +
                    userName.measuredHeight + userName.paddingBottom +
                    userName.paddingTop + reactions.measuredHeight,
        )
    }

    override fun generateDefaultLayoutParams(): LayoutParams {
        return MarginLayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
    }

    override fun generateLayoutParams(p: LayoutParams): LayoutParams {
        return MarginLayoutParams(p)
    }

    override fun generateLayoutParams(attrs: AttributeSet?): LayoutParams {
        return MarginLayoutParams(context, attrs)
    }

    override fun checkLayoutParams(p: LayoutParams): Boolean {
        return p is MarginLayoutParams
    }
}