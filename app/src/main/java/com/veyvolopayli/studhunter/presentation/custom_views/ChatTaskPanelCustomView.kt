package com.veyvolopayli.studhunter.presentation.custom_views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ArrayAdapter
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.veyvolopayli.studhunter.R
import com.veyvolopayli.studhunter.common.toPx
import com.veyvolopayli.studhunter.databinding.ChatTaskPanelCustomViewBinding

class ChatTaskPanelCustomView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {

    private val binding: ChatTaskPanelCustomViewBinding
    var onSendTaskRequestClick: (() -> Unit)? = null
    var onAcceptTaskClick: (() -> Unit)? = null
    var onDeclineTaskClick: (() -> Unit)? = null

    private var _timeToComplete: Long = 3600000
    val timeToComplete: Long
        get() = _timeToComplete

    init {
        val inflater = LayoutInflater.from(context)
        inflater.inflate(R.layout.chat_task_panel_custom_view, this, true)
        binding = ChatTaskPanelCustomViewBinding.bind(this)
        initAttrs(attrs)
    }

    private fun initAttrs(attrs: AttributeSet?) {
        attrs ?: return

        background =
            ResourcesCompat.getDrawable(resources, R.drawable.background_topbar, context.theme)
        setPadding(16.toPx(), 0, 16.toPx(), 0)
        minimumHeight = 64.toPx()

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.ChatTaskPanelCustomView)

        val panelType = typedArray.getInt(R.styleable.ChatTaskPanelCustomView_task_panel_type, 1)

        initPanelType(panelType = panelType)

        typedArray.recycle()
    }

    enum class Type {
        SELLER_REQUEST_RECEIVED,
        CUSTOMER_DEFAULT,
        AFTER_POSITIVE_RESPONSE,
        AFTER_NEGATIVE_RESPONSE,
        DEFAULT,
        CUSTOMER_REQUEST_SENT
    }

    private fun initPanelType(panelType: Int) {
        when (panelType) {

            PANEL_TYPE_DEFAULT -> {
                with(binding) {
                    customerTypeLayout.visibility = View.GONE
                    sellerTypeLayout.visibility = View.VISIBLE
                    afterResponseTypeLayout.visibility = View.GONE
                    sellerAcceptTaskButton.visibility = View.GONE
                    sellerDeclineTaskButton.visibility = View.GONE
                    newTaskTv.visibility = View.GONE
                }
            }

            PANEL_TYPE_CUSTOMER_DEFAULT -> {
                with(binding) {
                    customerTypeLayout.visibility = View.VISIBLE
                    sellerTypeLayout.visibility = View.GONE
                    afterResponseTypeLayout.visibility = View.GONE
                    customerSendDealRequestButton.setOnClickListener {
                        _timeToComplete = when(deadlineAutoCompleteTv.text.toString()) {
                            "1 час" -> 3600000
                            "4 часа" -> 3600000 * 4
                            "8 часов" -> 3600000 * 8
                            "16 часов" -> 3600000 * 16
                            "24 часа" -> 3600000 * 24
                            "4 дня" -> 3600000 * 24 * 4
                            "Неделя" -> 3600000 * 24 * 7
                            else -> 36000000
                        }
                        onSendTaskRequestClick?.invoke()
                    }
                    val adapter = ArrayAdapter(context, R.layout.item_autocomplete_textview_default,
                        listOf("1 час", "4 часа", "8 часов", "16 часов", "24 часа", "4 дня", "Неделя"))
                    deadlineAutoCompleteTv.apply {
                        setText("1 час")
                        setAdapter(adapter)
                        setDropDownBackgroundDrawable(
                            ContextCompat.getDrawable(
                                context,
                                R.drawable.background_12px
                            ))
                    }
                }
            }

            PANEL_TYPE_AFTER_POSITIVE_RESPONSE -> {
                with(binding) {
                    customerTypeLayout.visibility = View.GONE
                    sellerTypeLayout.visibility = View.GONE
                    afterResponseTypeLayout.visibility = View.VISIBLE
                    taskStatusTv.apply {
                        text = "Принято"
                        setTextColor(
                            ResourcesCompat.getColor(
                                resources,
                                R.color.green,
                                context.theme
                            )
                        )
                    }
                }
            }

            PANEL_TYPE_AFTER_NEGATIVE_RESPONSE -> {
                with(binding) {
                    customerTypeLayout.visibility = View.GONE
                    sellerTypeLayout.visibility = View.GONE
                    afterResponseTypeLayout.visibility = View.VISIBLE
                    taskStatusTv.apply {
                        text = "Отклонено"
                        setTextColor(
                            ResourcesCompat.getColor(
                                resources,
                                R.color.red,
                                context.theme
                            )
                        )
                    }
                }
            }

            PANEL_TYPE_SELLER_REQUEST_RECEIVED -> {
                with(binding) {
                    customerTypeLayout.visibility = View.GONE
                    sellerTypeLayout.visibility = View.VISIBLE
                    afterResponseTypeLayout.visibility = View.GONE
                    newTaskTv.visibility = View.VISIBLE
                    sellerAcceptTaskButton.apply {
                        visibility = View.VISIBLE
                        setOnClickListener {
                            onAcceptTaskClick?.invoke()
                        }
                    }
                    sellerDeclineTaskButton.apply {
                        visibility = View.VISIBLE
                        setOnClickListener {
                            onDeclineTaskClick?.invoke()
                        }
                    }
                }
            }

            PANEL_TYPE_CUSTOMER_REQUEST_SENT -> {
                with(binding) {
                    customerTypeLayout.visibility = View.VISIBLE
                    sellerTypeLayout.visibility = View.GONE
                    afterResponseTypeLayout.visibility = View.GONE
                    customerSendDealRequestButton.visibility = View.GONE
                    deadlineAutoCompleteTv.visibility = View.GONE
                    awaitingAnswerTv.apply {
                        visibility = View.VISIBLE
                        text = "Запрос отправлен"
                    }
                }
            }
        }
    }

    fun setPanelType(type: Type) {
        val panelType = when (type) {
            Type.DEFAULT -> PANEL_TYPE_DEFAULT
            Type.CUSTOMER_DEFAULT -> PANEL_TYPE_CUSTOMER_DEFAULT
            Type.AFTER_POSITIVE_RESPONSE -> PANEL_TYPE_AFTER_POSITIVE_RESPONSE
            Type.AFTER_NEGATIVE_RESPONSE -> PANEL_TYPE_AFTER_NEGATIVE_RESPONSE
            Type.SELLER_REQUEST_RECEIVED -> PANEL_TYPE_SELLER_REQUEST_RECEIVED
            Type.CUSTOMER_REQUEST_SENT -> PANEL_TYPE_CUSTOMER_REQUEST_SENT
        }
        initPanelType(panelType = panelType)
    }

    companion object {
        const val PANEL_TYPE_CUSTOMER_DEFAULT = 0
        const val PANEL_TYPE_DEFAULT = 1
        const val PANEL_TYPE_AFTER_POSITIVE_RESPONSE = 2
        const val PANEL_TYPE_AFTER_NEGATIVE_RESPONSE = 3
        const val PANEL_TYPE_SELLER_REQUEST_RECEIVED = 4
        const val PANEL_TYPE_CUSTOMER_REQUEST_SENT = 5
    }

}