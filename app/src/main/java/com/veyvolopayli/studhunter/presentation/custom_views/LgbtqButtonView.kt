package com.veyvolopayli.studhunter.presentation.custom_views

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.core.content.res.ResourcesCompat
import com.veyvolopayli.studhunter.R
import com.veyvolopayli.studhunter.databinding.LgbtqButtonViewBinding
import com.veyvolopayli.studhunter.presentation.custom_views.compose.ComposeGradientButtonDefault
import jp.wasabeef.blurry.Blurry

class LgbtqButtonView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
): FrameLayout(context, attrs) {

    private val binding: LgbtqButtonViewBinding

    var onClick: (() -> Unit)? = null

    init {
        val inflater = LayoutInflater.from(context)
        inflater.inflate(R.layout.lgbtq_button_view, this, true)
        binding = LgbtqButtonViewBinding.bind(this)
        initAttrs(context, attrs)
    }

    companion object {
        private const val PRIMARY = "primary"
        private const val SECONDARY = "secondary"
        private const val TERTIARY = "tertiary"
    }

    private fun initButton(typedArray: TypedArray) {
        with(binding) {

            val type = typedArray.getText(R.styleable.LgbtqButtonView_buttonType)

            val text = typedArray.getText(R.styleable.LgbtqButtonView_buttonText)

//            buttonText.text = text

            when(type) {
                PRIMARY -> {
                    val isActive = typedArray.getBoolean(R.styleable.LgbtqButtonView_isActive, true)
                    if (isActive) {
                        isClickable = true
                        button.setContent {
                            ComposeGradientButtonDefault(text = text.toString(), isClickable = isClickable) {
                                onClick?.invoke()
                            }
                        }
                    } else {
                        isClickable = false
                        button.setContent {
                            ComposeGradientButtonDefault(text = text.toString(), isClickable = isClickable) {
                                onClick?.invoke()
                            }
                        }
                    }
                }
                SECONDARY -> {
                    val isActive = typedArray.getBoolean(R.styleable.LgbtqButtonView_isActive, true)
                    if (isActive) {
                        isClickable = true
                        background = ResourcesCompat.getDrawable(resources, R.drawable.primary_button_background, context.theme)
                    } else {
                        isClickable = false
                        background = ResourcesCompat.getDrawable(resources, R.drawable.primary_button_background_disabled, context.theme)
                    }
                }
                TERTIARY -> {
                    val isActive = typedArray.getBoolean(R.styleable.LgbtqButtonView_isActive, true)
                    if (isActive) {
                        isClickable = true
                        background = ResourcesCompat.getDrawable(resources, R.drawable.primary_button_background, context.theme)
                    } else {
                        isClickable = false
                        background = ResourcesCompat.getDrawable(resources, R.drawable.primary_button_background_disabled, context.theme)
                    }
                }
                else -> {
                    val isActive = typedArray.getBoolean(R.styleable.LgbtqButtonView_isActive, true)
                    if (isActive) {
                        isClickable = true
                        background = ResourcesCompat.getDrawable(resources, R.drawable.primary_button_background, context.theme)
                    } else {
                        isClickable = false
                        background = ResourcesCompat.getDrawable(resources, R.drawable.primary_button_background_disabled, context.theme)
                    }
                }
            }
        }
    }

    private fun initAttrs(context: Context, attrs: AttributeSet?) {
        attrs ?: return
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.LgbtqButtonView)

        initButton(typedArray)

        typedArray.recycle()
    }

}