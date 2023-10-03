package com.veyvolopayli.studhunter.presentation.custom_views

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.lifecycle.lifecycleScope
import com.veyvolopayli.studhunter.R
import com.veyvolopayli.studhunter.common.toPx
import com.veyvolopayli.studhunter.databinding.StarsViewBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class StarsView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : LinearLayout(context, attrs) {

    private val binding: StarsViewBinding
    private val stars: List<ImageView>

    private var _value: Int? = null
    val value: Int?
        get() {
            return _value
        }

    init {
        val layoutInflater = LayoutInflater.from(context)
        layoutInflater.inflate(R.layout.stars_view, this, true)
        binding = StarsViewBinding.bind(this)

        minimumHeight = 50.toPx()
        gravity = Gravity.CENTER_VERTICAL

        stars = with(binding) { listOf(star1, star2, star3, star4, star5) }

        initAttrs(attrs)
    }

    private suspend fun animateStars(activeStars: List<ImageView>, anim: Animation) {
        stars.forEach {
            it.clearAnimation()
        }

        activeStars.forEach { starView ->
            starView.startAnimation(anim)
            delay(500)
        }
    }

    private fun colorStars(starsCount: Int) {
        stars.forEachIndexed { i, star ->
            star.imageTintList = if (i < starsCount) {
                ColorStateList.valueOf(
                    ResourcesCompat.getColor(resources, R.color.primary, context.theme)
                )
            } else {
                ColorStateList.valueOf(
                    ResourcesCompat.getColor(resources, R.color.tertiary, context.theme)
                )
            }
        }
    }

    private fun enableStarsTouch(w: Int) {
        val starRanges = List(5) { w * it / 5..w * (it + 1) / 5 }
//        val anim = AnimationUtils.loadAnimation(context, R.anim.bounce_heart_anim)
        setOnTouchListener { view, motionEvent ->
            if (motionEvent.action == MotionEvent.ACTION_DOWN) {
                view.performClick()
                val x = motionEvent.x.toInt()
                val starsCount = starRanges.indexOfFirst { x in it } + 1
                colorStars(starsCount)
                _value = starsCount
                /*val activeStars = stars.take(starsCount)
                findViewTreeLifecycleOwner()?.lifecycleScope?.launch {
                    animateStars(activeStars, anim)
                }*/
            }
            true
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        enableStarsTouch(w)
    }

    private fun initAttrs(attrs: AttributeSet?) {
        attrs ?: return
    }

}