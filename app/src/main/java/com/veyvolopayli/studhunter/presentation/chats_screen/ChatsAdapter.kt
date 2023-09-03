package com.veyvolopayli.studhunter.presentation.chats_screen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.veyvolopayli.studhunter.common.millsToDate
import com.veyvolopayli.studhunter.data.remote.dto.Chat
import com.veyvolopayli.studhunter.databinding.ItemChatBinding

class ChatsAdapter() : RecyclerView.Adapter<ChatsAdapter.ChatViewHolder>() {
    private var chats = listOf<Chat>()
    var onItemClick : ((Chat) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatsAdapter.ChatViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemChatBinding.inflate(layoutInflater, parent, false)

        return ChatViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        val chat = chats[position]
        holder.title.text = chat.publicationId
        holder.lastMessage.text = chat.lastMessage
        holder.date.text = chat.timestamp.millsToDate()
        holder.binding.chatLayout.setOnClickListener {
            onItemClick?.invoke(chat)
        }
    }

    override fun getItemCount(): Int = chats.size

    fun setData(chats: List<Chat>) {
        this.chats = chats
        notifyDataSetChanged()
    }

    inner class ChatViewHolder(val binding: ItemChatBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val title = binding.title
        val lastMessage = binding.lastMessage
        val date = binding.date
    }
}