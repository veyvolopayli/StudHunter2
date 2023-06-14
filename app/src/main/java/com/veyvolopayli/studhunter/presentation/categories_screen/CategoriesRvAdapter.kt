package com.veyvolopayli.studhunter.presentation.categories_screen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.veyvolopayli.studhunter.common.show
import com.veyvolopayli.studhunter.databinding.HomePublicationItemBinding
import com.veyvolopayli.studhunter.databinding.ItemCategoryBinding
import com.veyvolopayli.studhunter.domain.model.Publication

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
