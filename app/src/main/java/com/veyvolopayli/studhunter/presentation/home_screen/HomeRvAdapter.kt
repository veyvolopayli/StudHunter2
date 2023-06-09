package com.veyvolopayli.studhunter.presentation.home_screen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.veyvolopayli.studhunter.databinding.ItemMainBinding
import com.veyvolopayli.studhunter.domain.model.Publication
import java.lang.Exception

class HomeRvAdapter : RecyclerView.Adapter<HomeRvAdapter.ViewHolder>(), View.OnClickListener {

    var onItemClick : ((String) -> Unit)? = null
    private var publications: List<Publication> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
        val binding: ItemMainBinding = ItemMainBinding.inflate(view, parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = publications[position]

        with(holder.binding){
            titleMainItemTv.text = currentItem.title
            subtitleMainItemTv.text = currentItem.description
            priceMainItemTv.text = currentItem.price.toString()
            timeMainItemTv.text = currentItem.timestamp
            itemConstraintLayout.setOnClickListener {
                onItemClick?.invoke(currentItem.id)
            }

            try {
                Glide.with(itemTopImage.context).load(currentItem.imageUrl)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .placeholder(R.drawable.background_splash_white)
                    .into(itemTopImage)
            } catch (_: Exception){

            }
        }

    }

    fun setData(publications: List<Publication>) {
        this.publications = publications
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return publications.size
    }

    class ViewHolder(val binding: ItemMainBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onClick(v: View) {
        val publication = v.tag as Publication
    }
}
