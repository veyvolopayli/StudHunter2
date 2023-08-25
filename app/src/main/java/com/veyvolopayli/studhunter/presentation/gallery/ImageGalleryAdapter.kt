package com.veyvolopayli.studhunter.presentation.gallery

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.veyvolopayli.studhunter.R
import com.veyvolopayli.studhunter.common.hide
import com.veyvolopayli.studhunter.common.show
import com.veyvolopayli.studhunter.databinding.ItemGalleryImageBinding

class ImageGalleryAdapter(private val gallerySelectMode: GallerySelectMode = GallerySelectMode.Multiple) : RecyclerView.Adapter<ImageGalleryAdapter.ViewHolder>(),
    View.OnClickListener {

    var onItemClick: ((String) -> Unit)? = null
    private var images: List<String> = emptyList()

    private val _selectedImages: MutableList<String> = mutableListOf()
    val selectedImages: List<String> = _selectedImages

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
        val binding: ItemGalleryImageBinding = ItemGalleryImageBinding.inflate(view, parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = images[position]

        when(gallerySelectMode) {
            is GallerySelectMode.Multiple -> {
                with(holder.binding) {
                    Glide.with(image.context).load(currentItem)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .placeholder(R.drawable.background_24px)
                        .into(image)

                    image.setOnClickListener {
                        _selectedImages.apply {
                            if (currentItem in this) {
                                remove(currentItem)
                                filledView.hide()
                            }
                            else {
                                add(currentItem)
                                filledView.text = (_selectedImages.indexOf(currentItem) + 1).toString()
                                filledView.show()
                            }
                        }

                        onItemClick?.invoke(currentItem)
                    }

                    filledView.text = (_selectedImages.indexOf(currentItem) + 1).toString()

                    if (currentItem in _selectedImages) {
                        filledView.show()
                    } else {
                        filledView.hide()
                    }
                    Log.e("SHIT", "CALLED")

                }
            }
            is GallerySelectMode.Single -> {
                with(holder.binding) {
                    Glide.with(image).load(currentItem)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .placeholder(R.drawable.background_24px)
                        .into(image)
                    image.setOnClickListener {
                        _selectedImages.apply {
                            if (currentItem in this) {
                                remove(currentItem)
                                filledView.hide()
                            } else {
                                if (isEmpty()) add(0, currentItem)
                                else this[0] = currentItem
                                filledView.show()
                            }
                        }
                        onItemClick?.invoke(currentItem)
                        notifyDataSetChanged()
                    }
                    if (currentItem in _selectedImages) {
                        filledView.show()
                    } else {
                        filledView.hide()
                    }
                }
            }
        }

    }

    fun dataChanged() {
        _selectedImages.forEachIndexed { index, s ->
            notifyItemChanged(images.indexOf(s))
        }
    }

    fun setData(images: List<String>) {
        this.images = images
        Log.e("IMAGES", images.toString())
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return images.size
    }

    class ViewHolder(val binding: ItemGalleryImageBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onClick(v: View) {

    }
}
