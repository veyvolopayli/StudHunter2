package com.veyvolopayli.studhunter.presentation.user_chat_screen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.veyvolopayli.studhunter.databinding.ItemChatIncomingMessageBinding
import com.veyvolopayli.studhunter.databinding.ItemChatOutgoingMessageBinding
import com.veyvolopayli.studhunter.domain.model.Message

class UserChatAdapter(private val currentUserID: String) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var messages = listOf<Message>()

    companion object {
        private const val INCOMING_MESSAGE_VIEW_TYPE = 0
        private const val OUTGOING_MESSAGE_VIEW_TYPE = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            INCOMING_MESSAGE_VIEW_TYPE -> {
                val binding = ItemChatIncomingMessageBinding.inflate(layoutInflater, parent, false)
                IncomingMessageViewHolder(binding)
            }
            OUTGOING_MESSAGE_VIEW_TYPE -> {
                val binding = ItemChatOutgoingMessageBinding.inflate(layoutInflater, parent, false)
                OutgoingMessageViewHolder(binding)
            }
            else -> {
                throw IllegalStateException("")
            }
        }
    }

    override fun getItemCount(): Int = messages.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val message = messages[position]
        when (holder) {
            is IncomingMessageViewHolder -> {
                holder.messageBody.text = message.messageBody
                holder.timestamp.text = message.timestamp
            }
            is OutgoingMessageViewHolder -> {
                holder.messageBody.text = message.messageBody
                holder.timestamp.text = message.timestamp
            }
        }
    }

    inner class IncomingMessageViewHolder(binding: ItemChatIncomingMessageBinding) : RecyclerView.ViewHolder(binding.root) {
        val messageBody = binding.messageBody
        val timestamp = binding.timestamp
    }

    inner class OutgoingMessageViewHolder(binding: ItemChatOutgoingMessageBinding) : RecyclerView.ViewHolder(binding.root) {
        val messageBody = binding.messageBody
        val timestamp = binding.timestamp
    }

    fun newMessage(messages: List<Message>) {
        this.messages = messages
        notifyItemInserted(0)
    }

    override fun getItemViewType(position: Int): Int {
        val message = messages[position]
        return if (message.userID == currentUserID) {
            OUTGOING_MESSAGE_VIEW_TYPE
        } else INCOMING_MESSAGE_VIEW_TYPE
    }
}