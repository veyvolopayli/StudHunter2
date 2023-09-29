package com.veyvolopayli.studhunter.presentation.custom_views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.cardview.widget.CardView
import com.veyvolopayli.studhunter.R
import com.veyvolopayli.studhunter.databinding.LeaveReviewHomeNotificationViewBinding

class LeaveReviewNotificationView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
): CardView(context, attrs) {

    private val binding: LeaveReviewHomeNotificationViewBinding

    init {
        val inflater = LayoutInflater.from(context)
        inflater.inflate(R.layout.leave_review_home_notification_view, this, true)
        binding = LeaveReviewHomeNotificationViewBinding.bind(this)
        initAttrs(attrs)
    }

    private fun initAttrs(attrs: AttributeSet?) {
        if (attrs != null) {
            val typedArray = context.obtainStyledAttributes(attrs, R.styleable.LeaveReviewNotificationView)

            val cardColor = typedArray.getColor(R.styleable.LeaveReviewNotificationView_leaveReviewCardColor, resources.getColor(R.color.tertiary, context.theme))
            setCardBackgroundColor(cardColor)

            val cardRadius = typedArray.getDimension(R.styleable.LeaveReviewNotificationView_leaveReviewCardRadius, 8f)
            radius = cardRadius

            typedArray.recycle()
        } else {
            setCardBackgroundColor(resources.getColor(R.color.tertiary, context.theme))
            radius = 16f
        }
    }

    fun startAnim() {
        val star = binding.star

        AnimationUtils.loadAnimation(context, R.anim.jump_star_anim).also { anim ->
            star.startAnimation(anim)
            anim.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(animation: Animation?) {}

                override fun onAnimationEnd(animation: Animation?) {
                    star.startAnimation(anim)
                }

                override fun onAnimationRepeat(animation: Animation?) {}

            })
        }
    }

}