package com.veyvolopayli.studhunter.presentation.custom_views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import com.google.android.flexbox.FlexboxLayout
import com.veyvolopayli.studhunter.R
import com.veyvolopayli.studhunter.databinding.RatignsCustomViewBinding

class RatingsCustomView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
): FlexboxLayout(context, attrs) {

    private val binding: RatignsCustomViewBinding

    enum class ActiveRatingCell {
        ANY, ONE, TWO, THREE, FOUR, FIVE
    }

    private var activeCell: ActiveRatingCell

    var chosenRating: Int? = 0

    init {
        val inflater = LayoutInflater.from(context)
        inflater.inflate(R.layout.ratigns_custom_view, this, true)
        binding = RatignsCustomViewBinding.bind(this)
        activeCell = ActiveRatingCell.ANY

        initAttrs(attrs)
    }

    private fun initAttrs(attrs: AttributeSet?) {
        attrs ?: return
        val types = context.obtainStyledAttributes(attrs, R.styleable.RatingsCustomView)

        val activeRatingCell = types.getInt(R.styleable.RatingsCustomView_activeRatingCell, 0)
        activeCell = when(activeRatingCell) {
            0 -> ActiveRatingCell.ANY
            5 -> ActiveRatingCell.FIVE
            4 -> ActiveRatingCell.FOUR
            3 -> ActiveRatingCell.THREE
            2 -> ActiveRatingCell.TWO
            1 -> ActiveRatingCell.ONE
            else -> ActiveRatingCell.ANY
        }

        when(activeCell) {
            ActiveRatingCell.ANY -> {
                binding.ratingAny.changeChosenStatus()
            }
            ActiveRatingCell.FIVE -> {
                binding.ratingFivePlus.changeChosenStatus()
            }
            ActiveRatingCell.FOUR -> {
                binding.ratingFourPlus.changeChosenStatus()
            }
            ActiveRatingCell.THREE -> {
                binding.ratingThreePlus.changeChosenStatus()
            }
            ActiveRatingCell.TWO -> {
                binding.ratingTwoPlus.changeChosenStatus()
            }
            ActiveRatingCell.ONE -> {
                binding.ratingOnePlus.changeChosenStatus()
            }
        }

        with(binding) {
            ratingAny.setOnClickListener {
                disableActiveCell(activeRatingCell = activeCell)
                activeCell = ActiveRatingCell.ANY
                binding.ratingAny.changeChosenStatus()
                chosenRating = null
            }
            ratingFivePlus.setOnClickListener {
                disableActiveCell(activeRatingCell = activeCell)
                activeCell = ActiveRatingCell.FIVE
                binding.ratingFivePlus.changeChosenStatus()
                chosenRating = 5
            }
            ratingFourPlus.setOnClickListener {
                disableActiveCell(activeRatingCell = activeCell)
                activeCell = ActiveRatingCell.FOUR
                binding.ratingFourPlus.changeChosenStatus()
                chosenRating = 4
            }
            ratingThreePlus.setOnClickListener {
                disableActiveCell(activeRatingCell = activeCell)
                activeCell = ActiveRatingCell.THREE
                binding.ratingThreePlus.changeChosenStatus()
                chosenRating = 3
            }
            ratingTwoPlus.setOnClickListener {
                disableActiveCell(activeRatingCell = activeCell)
                activeCell = ActiveRatingCell.TWO
                binding.ratingTwoPlus.changeChosenStatus()
                chosenRating = 2
            }
            ratingOnePlus.setOnClickListener {
                disableActiveCell(activeRatingCell = activeCell)
                activeCell = ActiveRatingCell.ONE
                binding.ratingOnePlus.changeChosenStatus()
                chosenRating = 1
            }
        }

        types.recycle()
    }

    private fun disableActiveCell(activeRatingCell: ActiveRatingCell) {
        when(activeRatingCell) {
            ActiveRatingCell.ANY -> {
                binding.ratingAny.changeChosenStatus()
            }
            ActiveRatingCell.FIVE -> {
                binding.ratingFivePlus.changeChosenStatus()
            }
            ActiveRatingCell.FOUR -> {
                binding.ratingFourPlus.changeChosenStatus()
            }
            ActiveRatingCell.THREE -> {
                binding.ratingThreePlus.changeChosenStatus()
            }
            ActiveRatingCell.TWO -> {
                binding.ratingTwoPlus.changeChosenStatus()
            }
            ActiveRatingCell.ONE -> {
                binding.ratingOnePlus.changeChosenStatus()
            }
        }
    }
}