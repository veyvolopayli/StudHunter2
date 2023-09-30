package com.veyvolopayli.studhunter.presentation.custom_views

import android.content.Context
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.widget.LinearLayout
import com.veyvolopayli.studhunter.R
import com.veyvolopayli.studhunter.databinding.StarsViewBinding

class StarsView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : LinearLayout(context, attrs) {

    private val binding: StarsViewBinding

    init {
        val layoutInflater = LayoutInflater.from(context)
        layoutInflater.inflate(R.layout.stars_view, this, true)
        binding = StarsViewBinding.bind(this)

        initAttrs(attrs)
    }

    private fun initAttrs(attrs: AttributeSet?) {
        attrs ?: return
    }

}