package com.veyvolopayli.studhunter.presentation.publication_screen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.veyvolopayli.studhunter.R
import com.veyvolopayli.studhunter.databinding.ItemImagePublicationBinding


class ImagesAdapter : RecyclerView.Adapter<ImagesAdapter.ViewHolder>() {

    private var images: List<String> = emptyList()

    var onClick : ((Int) -> Unit)? = null

    class ViewHolder(val binding: ItemImagePublicationBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemImagePublicationBinding.inflate(inflater, parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentImage = images[position]

        with(holder.binding) {
            Glide.with(root).load(currentImage).placeholder(R.drawable.background_24px).into(image)
            imageNum.text = "${position + 1} - ${images.size}" ?: ""
            image.setOnClickListener {
                onClick?.invoke(position)
            }
        }
    }

    fun setImages(images: List<String>) {
        this.images = images
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return images.size
    }

}