package com.veyvolopayli.studhunter.presentation.publications_filter_screen

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.veyvolopayli.studhunter.databinding.ItemFilterCategoryBinding

class CategoriesListAdapter : RecyclerView.Adapter<CategoriesListAdapter.ViewHolder>() {

    private var categories = listOf<String>()
    private var onClick: ((String) -> Unit)? = null
    private var _chosenCategories = mutableListOf<String>()
    val chosenCategories: List<String> = _chosenCategories

    class ViewHolder(binding: ItemFilterCategoryBinding): RecyclerView.ViewHolder(binding.root) {
        val root = binding.root
        val categoryView = binding.categoryView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemFilterCategoryBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val category = categories[position]
        holder.categoryView.setCategoryText(category)

        holder.root.setOnClickListener {
            onClick?.invoke(category)
            val isChosenNow = holder.categoryView.changeChosenStatus()
            if (isChosenNow) {
                _chosenCategories.add(category)
            } else {
                _chosenCategories.remove(category)
            }
            Log.e("", _chosenCategories.toString())
        }
    }

    override fun getItemCount(): Int = categories.size

    fun setData(data: List<String>) {
        categories = data
        notifyDataSetChanged()
    }
}
