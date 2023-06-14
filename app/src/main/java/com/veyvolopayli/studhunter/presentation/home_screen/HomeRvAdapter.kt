package com.veyvolopayli.studhunter.presentation.home_screen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.veyvolopayli.studhunter.common.show
import com.veyvolopayli.studhunter.databinding.HomePublicationItemBinding
import com.veyvolopayli.studhunter.domain.model.Publication

class HomeRvAdapter : RecyclerView.Adapter<HomeRvAdapter.ViewHolder>(), View.OnClickListener {

    var onItemClick : ((String) -> Unit)? = null
    private var publications: List<Publication> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
        val binding: HomePublicationItemBinding = HomePublicationItemBinding.inflate(view, parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = publications[position]

        with(holder.binding){
            currentItem.price?.let {
                price.text = it.toString()
                price.show()
            }
            title.text = currentItem.title
            description.text = currentItem.description
            priceType.text = currentItem.priceType
            dateTime.text = currentItem.timestamp
            itemConstraintLayout.setOnClickListener {
                onItemClick?.invoke(currentItem.id)
            }

            try {
                Glide.with(image.context).load(currentItem.imageUrl)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .placeholder(R.drawable.background_splash_white)
                    .into(image)
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

    class ViewHolder(val binding: HomePublicationItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onClick(v: View) {
        val publication = v.tag as Publication
    }
}
