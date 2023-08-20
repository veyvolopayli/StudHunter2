package com.veyvolopayli.studhunter.presentation.my_publications_screen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.veyvolopayli.studhunter.data.remote.dto.MyPublicationDTO
import com.veyvolopayli.studhunter.databinding.ItemMyPublicationBinding
import com.veyvolopayli.studhunter.databinding.ItemPublicationHomeBinding
import com.veyvolopayli.studhunter.domain.model.Message
import com.veyvolopayli.studhunter.domain.model.Publication

class MyPublicationsAdapter() : RecyclerView.Adapter<MyPublicationsAdapter.PublicationViewHolder>() {
    private var publications = listOf<MyPublicationDTO>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyPublicationsAdapter.PublicationViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemMyPublicationBinding.inflate(layoutInflater, parent, false)
        return PublicationViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PublicationViewHolder, position: Int) {
        val publication = publications[position]
        with(holder.binding) {
            title.text = publication.title
            description.text = publication.description
            val priceWithType = "${ publication.price ?: "" } ${publication.priceType}".trim()
            price.text = priceWithType
            views.text = publication.views.toString()
            likes.text = publication.favorites.toString()
            Glide.with(image.context).load(publication.imageUrl).into(image)
        }

    }

    override fun getItemCount(): Int = publications.size

    inner class PublicationViewHolder(val binding: ItemMyPublicationBinding) : RecyclerView.ViewHolder(binding.root)

    fun setPublications(publications: List<MyPublicationDTO>) {
        this.publications = publications
        notifyDataSetChanged()
    }
}