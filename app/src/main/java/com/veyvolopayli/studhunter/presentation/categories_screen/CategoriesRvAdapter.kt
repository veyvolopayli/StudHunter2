package com.veyvolopayli.studhunter.presentation.categories_screen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.veyvolopayli.studhunter.databinding.ItemCategoryBinding

class CategoriesRvAdapter : RecyclerView.Adapter<CategoriesRvAdapter.ViewHolder>() {

    var onItemClick : ((String) -> Unit)? = null
    private var categories: Map<Int, String> = emptyMap()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
        val binding: ItemCategoryBinding = ItemCategoryBinding.inflate(view, parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = categories[position]

        with(holder.binding){
            name.text = currentItem
            itemCategoryCardView.setOnClickListener {
                onItemClick?.invoke(currentItem ?: "")
            }
        }

    }

    fun setData(categories: Map<Int, String>) {
        this.categories = categories
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    class ViewHolder(val binding: ItemCategoryBinding) : RecyclerView.ViewHolder(binding.root)

}
