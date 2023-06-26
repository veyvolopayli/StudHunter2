package com.veyvolopayli.studhunter.presentation.create_publication_screen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.veyvolopayli.studhunter.R
import com.veyvolopayli.studhunter.common.show
import com.veyvolopayli.studhunter.databinding.ItemCreatePublicationImageBinding

class CreatePublicationImagesAdapter : RecyclerView.Adapter<CreatePublicationImagesAdapter.ViewHolder>() {

    var onItemClick : ((String) -> Unit)? = null
    private var images: List<String> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
        val binding: ItemCreatePublicationImageBinding = ItemCreatePublicationImageBinding.inflate(view, parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = if (images.isEmpty()) R.drawable.create_publication_temp_image else images[position]

        with(holder.binding){
            Glide.with(image.context).load(currentItem)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .placeholder(R.drawable.background_splash_white)
                .into(image)

            image.setOnClickListener {
                onItemClick?.invoke(if (currentItem is String) currentItem else "")
            }
        }

    }

    fun setData(images: List<String>) {
        this.images = images
        notifyDataSetChanged()
    }

    fun getSelectedImages() = images

    override fun getItemCount(): Int {
        return if (images.isEmpty()) 1 else images.size
    }

    class ViewHolder(val binding: ItemCreatePublicationImageBinding) : RecyclerView.ViewHolder(binding.root)
}
