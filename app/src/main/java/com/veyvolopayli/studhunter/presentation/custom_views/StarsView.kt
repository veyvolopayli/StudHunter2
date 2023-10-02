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

@SuppressLint("ClickableViewAccessibility")
class StarsView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : LinearLayout(context, attrs) {

    private val binding: StarsViewBinding
    private val stars: List<ImageView>

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
            it.imageTintList = ColorStateList.valueOf(
                ResourcesCompat.getColor(resources, R.color.tertiary, context.theme)
            )
        }
//        val activeStars = stars.take(count)
        activeStars.forEach { starView ->
            starView.imageTintList = ColorStateList.valueOf(
                ResourcesCompat.getColor(resources, R.color.primary, context.theme)
            )
            starView.startAnimation(anim)
            delay(200)
        }
    }

    private fun enableStarsTouch(w: Int) {
        val starRanges = List(5) { w * it / 5..w * (it + 1) / 5 }
        val anim = AnimationUtils.loadAnimation(context, R.anim.bounce_heart_anim)
        setOnTouchListener { _, motionEvent ->
            if (motionEvent.action == MotionEvent.ACTION_DOWN) {
                val x = motionEvent.x.toInt()
                val starsCount = starRanges.indexOfFirst { x in it } + 1
                val activeStars = stars.take(starsCount)
                findViewTreeLifecycleOwner()?.lifecycleScope?.launch {
                    animateStars(activeStars, anim)
                }
            }
            true
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        enableStarsTouch(w)

        // todo Для жестов

//        val starRanges = List(5) { w * it / 5..w * (it + 1) / 5 }
//
//        var previousRange: IntRange = 0..1
//
//        setOnTouchListener { view, event ->
//            if (event.action == MotionEvent.ACTION_MOVE) {
//                val x = event.x.toInt()
//                val currentRange = starRanges.firstOrNull { x in it }
//
//                if (currentRange != null && currentRange != previousRange) {
//                    previousRange = currentRange
//                    val starIndex = starRanges.indexOf(currentRange) + 1
//                    Log.e("TAG", starIndex.toString())
//                }
//            }
//            true
//        }

    }

    private fun initAttrs(attrs: AttributeSet?) {
        attrs ?: return
    }

}