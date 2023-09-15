package com.veyvolopayli.studhunter.presentation.custom_views

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.core.content.res.ResourcesCompat
import com.veyvolopayli.studhunter.R
import com.veyvolopayli.studhunter.databinding.FilterCategoryViewBinding

class FilterCategoryView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {

    private val binding: FilterCategoryViewBinding
    private var _isChosen = false
    val isChosen = _isChosen

    private val primaryColor: Int
    private val tertiaryColor: Int

    private val enabledBackground: Drawable?
    private val disabledBackground: Drawable?

    init {
        val inflater = LayoutInflater.from(context)
        inflater.inflate(R.layout.filter_category_view, this, true)
        binding = FilterCategoryViewBinding.bind(this)

        primaryColor = ResourcesCompat.getColor(resources, R.color.primary, context.theme)
        tertiaryColor = ResourcesCompat.getColor(resources, R.color.tertiary, context.theme)

        enabledBackground = ResourcesCompat.getDrawable(
            resources,
            R.drawable.outlined_background_primary_color,
            context.theme
        )
        disabledBackground = ResourcesCompat.getDrawable(
            resources,
            R.drawable.outlined_background_tertiary_color,
            context.theme
        )
        initAttrs(attrs)
    }

    private fun initAttrs(attrs: AttributeSet?) {
        attrs ?: return

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.FilterCategoryView)

        val categoryName = typedArray.getString(R.styleable.FilterCategoryView_categoryName)

        binding.nameCategory.text = categoryName

        val categoryIsChosen =
            typedArray.getBoolean(R.styleable.FilterCategoryView_categoryIsChosen, false)

        if (categoryIsChosen) {
            background = enabledBackground
            binding.nameCategory.setTextColor(primaryColor)
        } else {
            background = disabledBackground
            binding.nameCategory.setTextColor(tertiaryColor)
        }

        typedArray.recycle()
    }

    fun changeChosenStatus(): Boolean {
        this._isChosen = !_isChosen

        if (_isChosen) {
            background = enabledBackground
            binding.nameCategory.setTextColor(primaryColor)
        } else {
            background = disabledBackground
            binding.nameCategory.setTextColor(tertiaryColor)
        }

        return _isChosen
    }

    fun setCategoryText(category: String) {
        binding.nameCategory.text = category
    }

}