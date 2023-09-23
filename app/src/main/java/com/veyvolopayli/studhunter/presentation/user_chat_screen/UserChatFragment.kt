package com.veyvolopayli.studhunter.presentation.user_chat_screen

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.veyvolopayli.studhunter.R
import com.veyvolopayli.studhunter.databinding.FragmentUserChatBinding
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
            }

            chatID?.let {
                viewModel.getMessagesByChatId(chatId = it)
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

            if (currentUserID == sellerID) {
                binding.sendOfferLayout.visibility = View.GONE
            } else {
                with(binding) {
                    sendOfferLayout.visibility = View.VISIBLE
                    sendOfferButton.setOnClickListener {
                        viewModel.sendOfferRequest(86400000)
                        sendOfferButton.isClickable = false
                    }

                }
            }
        }

        binding.sendOfferLayout.visibility = View.VISIBLE

        viewModel.task.observe(viewLifecycleOwner) { task ->
            currentUserId?.let { userId ->
                if (userId == task.executorId) {
                    if (task.status.isEmpty()) {
                        with(binding) {
                            sendOfferResponseLayout.visibility = View.VISIBLE
                            acceptOfferButton.setOnClickListener {
                                viewModel.sendOfferResponse(true)
                            }
                            rejectOfferButton.setOnClickListener {
                                viewModel.sendOfferResponse(false)
                            }
                        }
                    } else if (task.status.isNotEmpty()) {
                        with(binding) {
                            responseSentTv.visibility = View.VISIBLE
                            acceptOfferButton.visibility = View.GONE
                            rejectOfferButton.visibility = View.GONE
                        }
                    }
                }

                if (userId == task.customerId) {
                    if (task.status.isEmpty()) {
                        with(binding) {
                            sentTv.visibility = View.VISIBLE
                            sendOfferButton.visibility = View.GONE
                        }
                    } else if (task.status == "accepted") {
                        binding.sentTv.text = "Принято"
                    } else if (task.status == "declined") {
                        binding.sentTv.text = "Отклонено"
                    }
                }
            }

        }

        viewModel.dealRequestState.observe(viewLifecycleOwner) { dealRequest ->
            binding.apply {
                sendOfferResponseLayout.visibility = View.VISIBLE
                acceptOfferButton.setOnClickListener {
                    viewModel.sendOfferResponse(true)
                }
                rejectOfferButton.setOnClickListener {
                    viewModel.sendOfferResponse(false)
                }
            }
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