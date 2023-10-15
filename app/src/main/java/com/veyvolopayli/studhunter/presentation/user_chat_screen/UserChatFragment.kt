package com.veyvolopayli.studhunter.presentation.user_chat_screen

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.veyvolopayli.studhunter.R
import com.veyvolopayli.studhunter.databinding.FragmentUserChatBinding
import com.veyvolopayli.studhunter.presentation.custom_views.ChatTaskPanelCustomView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserChatFragment : Fragment(R.layout.fragment_user_chat) {

    private var binding: FragmentUserChatBinding? = null
    private val viewModel: UserChatViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentUserChatBinding.bind(view)
        this.binding = binding

        val pubID = arguments?.getString("pub_id")
        val chatID = arguments?.getString("chat_id")
        val sellerID = arguments?.getString("seller_id")

        var currentUserId: String? = null

        var messagesAdapter: UserChatAdapter? = null

        if (savedInstanceState == null) {
            pubID?.let {
                viewModel.getMessagesByPublicationId(publicationId = it)
                viewModel.getTaskByPubId(pubId = it)
            }

            chatID?.let {
                viewModel.getMessagesByChatId(chatId = it)
                viewModel.getTaskByChatId(chatId = chatID)
            }
        }

        pubID?.let {
            viewModel.observeMessagesFromNewChat(publicationID = it)
        }

        chatID?.let {
            viewModel.observeMessagesFromExistingChat(chatID = it)
        }

        viewModel.currentUserID.observe(viewLifecycleOwner) { currentUserID ->
            currentUserId = currentUserID
            messagesAdapter = UserChatAdapter(currentUserID = currentUserID)
            binding.chatRv.layoutManager =
                LinearLayoutManager(requireContext()).also { it.reverseLayout = true }
            binding.chatRv.adapter = messagesAdapter
        }

        viewModel.task.observe(viewLifecycleOwner) { task ->
            currentUserId?.let { userId ->
                if (task == null) {
                    if (currentUserId == sellerID) {
                        binding.taskPanel.setPanelType(ChatTaskPanelCustomView.Type.DEFAULT)
                    } else {
                        binding.taskPanel.apply {
                            setPanelType(ChatTaskPanelCustomView.Type.CUSTOMER_DEFAULT)
                            onSendTaskRequestClick = {
                                val time = timeToComplete
                                if (time >= 3600000) viewModel.sendOfferRequest(time)
                            }
                        }
                    }
                } else {
                    if (userId == task.executorId) {
                        when(task.status.trim()) {
                            "accepted" -> {
                                binding.taskPanel.setPanelType(ChatTaskPanelCustomView.Type.AFTER_POSITIVE_RESPONSE)
                            }
                            "declined" -> {
                                binding.taskPanel.setPanelType(ChatTaskPanelCustomView.Type.AFTER_NEGATIVE_RESPONSE)
                            }
                            "" -> {
                                binding.taskPanel.apply {
                                    setPanelType(ChatTaskPanelCustomView.Type.SELLER_REQUEST_RECEIVED)
                                    onAcceptTaskClick = {
                                        viewModel.sendOfferResponse(isAccepted = true)
                                    }
                                    onDeclineTaskClick = {
                                        viewModel.sendOfferResponse(isAccepted = false)
                                    }
                                }
                            }
                        }
                    }

                    if (userId == task.customerId) {
                        when(task.status.trim()) {
                            "accepted" -> {
                                binding.taskPanel.setPanelType(ChatTaskPanelCustomView.Type.AFTER_POSITIVE_RESPONSE)
                            }
                            "declined" -> {
                                binding.taskPanel.setPanelType(ChatTaskPanelCustomView.Type.AFTER_NEGATIVE_RESPONSE)
                            }
                            "" -> {
                                binding.taskPanel.setPanelType(ChatTaskPanelCustomView.Type.CUSTOMER_REQUEST_SENT)
                            }
                        }
                    }
                }
            }

        }

        viewModel.dealRequestState.observe(viewLifecycleOwner) { dealRequest ->
            /*binding.apply {
                sendOfferResponseLayout.visibility = View.VISIBLE
                acceptOfferButton.setOnClickListener {
                    viewModel.sendOfferResponse(true)
                }
                rejectOfferButton.setOnClickListener {
                    viewModel.sendOfferResponse(false)
                }
            }*/
        }

        binding.sendButton.setOnClickListener {
            val messageBody = binding.textField.text.toString().trim()
            val type = "text"

            if (messageBody.isNotBlank()) {
                viewModel.sendMessage(messageBody = messageBody, type = type)
                binding.textField.text?.clear()
            }
        }

        viewModel.chatMessagesState.observe(viewLifecycleOwner) { messages ->
            messagesAdapter?.newMessage(messages)
            binding.chatRv.smoothScrollToPosition(0)
        }

        viewModel.toastEvent.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
        viewModel.disconnect()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.disconnect()
    }
}