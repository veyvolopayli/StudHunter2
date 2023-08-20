package com.veyvolopayli.studhunter.presentation.custom_views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import com.veyvolopayli.studhunter.R
import com.veyvolopayli.studhunter.databinding.HeartViewBinding

class HeartView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {

    private val binding: HeartViewBinding
    private val anim: Animation

    init {
        val inflater = LayoutInflater.from(context)
        inflater.inflate(R.layout.heart_view, this, true)
        binding = HeartViewBinding.bind(this)
        initAttrs(attrs)

        anim = AnimationUtils.loadAnimation(context, R.anim.bounce_heart_anim)
    }

    private fun initAttrs(attrs: AttributeSet?) {
        attrs ?: return

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.HeartView)

        val isChecked = typedArray.getBoolean(R.styleable.HeartView_isChecked, false)
        binding.heart.setColorFilter(
            if (isChecked) ContextCompat.getColor(context, R.color.red)
            else ContextCompat.getColor(context, R.color.black)
        )

        typedArray.recycle()
    }

    fun setChecked(isChecked: Boolean) {
        binding.heart.apply {
            startAnimation(anim)
            setColorFilter(
                if (isChecked) ContextCompat.getColor(context, R.color.red)
                else ContextCompat.getColor(context, R.color.black)
            )
        }
    }
}