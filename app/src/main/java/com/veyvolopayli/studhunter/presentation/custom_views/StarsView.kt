package com.veyvolopayli.studhunter.presentation.custom_views

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.GestureDetector
import android.view.Gravity
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.widget.LinearLayout
import com.veyvolopayli.studhunter.R
import com.veyvolopayli.studhunter.common.toPx
import com.veyvolopayli.studhunter.databinding.StarsViewBinding

@SuppressLint("ClickableViewAccessibility")
class StarsView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : LinearLayout(context, attrs) {

    private val binding: StarsViewBinding

    init {
        val layoutInflater = LayoutInflater.from(context)
        layoutInflater.inflate(R.layout.stars_view, this, true)
        binding = StarsViewBinding.bind(this)

        Log.e("F", width.toString())

        val firstStarRange = 0..width/5
        val secondStarRange = width/5..width/5*2
        val thirdStarRange = width/5*2..width/5*3
        val fourthStarRange = width/5*3..width/5*4
        val fifthStarRange = width/5*4..width

        setOnTouchListener { view, event ->
            if (event.action == MotionEvent.ACTION_MOVE) {
                if (event.x.toInt() in firstStarRange) {
                    Log.e("TAG", "1")
                }
                if (event.x.toInt() in secondStarRange) {
                    Log.e("TAG", "2")
                }
                if (event.x.toInt() in thirdStarRange) {
                    Log.e("TAG", "3")
                }
                if (event.x.toInt() in fourthStarRange) {
                    Log.e("TAG", "4")
                }
                if (event.x.toInt() in fifthStarRange) {
                    Log.e("TAG", "5")
                }
            }

            true
        }

        initAttrs(attrs)
    }

    private fun initAttrs(attrs: AttributeSet?) {
        minimumHeight = 50.toPx()
        gravity = Gravity.CENTER_VERTICAL
        attrs ?: return
    }

}