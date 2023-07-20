package com.veyvolopayli.studhunter.presentation.universities

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.veyvolopayli.studhunter.databinding.ItemDistrictBinding
import com.veyvolopayli.studhunter.databinding.ItemSimpleBinding

class UniversitiesAdapter : RecyclerView.Adapter<UniversitiesAdapter.ViewHolder>() {

    var onItemClick: ((String) -> Unit)? = null
    private var universities: List<String> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
        val binding: ItemSimpleBinding = ItemSimpleBinding.inflate(view, parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = universities[position]

        with(holder.binding) {
            name.text = currentItem
            name.setOnClickListener {
                onItemClick?.invoke(currentItem)
            }
        }
    }

    fun setData(universities: List<String>) {
        this.universities = universities
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return universities.size
    }

    class ViewHolder(val binding: ItemSimpleBinding) : RecyclerView.ViewHolder(binding.root)
}
