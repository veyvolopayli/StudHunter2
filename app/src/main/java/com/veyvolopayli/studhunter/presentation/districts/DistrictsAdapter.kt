package com.veyvolopayli.studhunter.presentation.districts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.veyvolopayli.studhunter.databinding.ItemDistrictBinding

class DistrictsAdapter : RecyclerView.Adapter<DistrictsAdapter.ViewHolder>() {

    var onItemClick: ((String) -> Unit)? = null
    private var districts: List<String> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
        val binding: ItemDistrictBinding = ItemDistrictBinding.inflate(view, parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = districts[position]

        with(holder.binding) {
            districtName.text = currentItem
            districtName.setOnClickListener {
                onItemClick?.invoke(currentItem)
            }
        }
    }

    fun setData(districts: List<String>) {
        this.districts = districts
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return districts.size
    }

    class ViewHolder(val binding: ItemDistrictBinding) : RecyclerView.ViewHolder(binding.root)
}
