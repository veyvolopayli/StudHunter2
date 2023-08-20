package com.veyvolopayli.studhunter.presentation.user_publications_screen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.veyvolopayli.studhunter.databinding.ItemPublicationHomeBinding
import com.veyvolopayli.studhunter.domain.model.Message
import com.veyvolopayli.studhunter.domain.model.Publication

class UserPublicationsAdapter() : RecyclerView.Adapter<UserPublicationsAdapter.PublicationViewHolder>() {
    private var publications = listOf<Publication>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserPublicationsAdapter.PublicationViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemPublicationHomeBinding.inflate(layoutInflater, parent, false)
        return PublicationViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PublicationViewHolder, position: Int) {
        val publication = publications[position]
        with(holder.binding) {
            title.text = publication.title
            description.text = publication.description
            dateTime.text = publication.timestamp
            price.text = publication.price.toString()
            priceType.text = publication.priceType

            Glide.with(image.context).load(publication.imageUrl).into(image)
        }

    }

    override fun getItemCount(): Int = publications.size

    inner class PublicationViewHolder(val binding: ItemPublicationHomeBinding) : RecyclerView.ViewHolder(binding.root)

    fun setPublications(publications: List<Publication>) {
        this.publications = publications
        notifyDataSetChanged()
    }
}