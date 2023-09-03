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

        var messagesAdapter: UserChatAdapter? = null

        pubID?.let {
            viewModel.observeMessagesFromNewChat(publicationID = it)
        }

        chatID?.let {
            viewModel.observeMessagesFromExistingChat(chatID = it)
        }

        viewModel.currentUserID.observe(viewLifecycleOwner) { currentUserID ->
            messagesAdapter = UserChatAdapter(currentUserID = currentUserID)
            binding.chatRv.layoutManager =
                LinearLayoutManager(requireContext()).also { it.reverseLayout = true }
            binding.chatRv.adapter = messagesAdapter
        }

        binding.sendOfferLayout.visibility = View.VISIBLE

        viewModel.offerRequestState.observe(viewLifecycleOwner) { offerRequest ->
            with(binding) {
                sendOfferLayout.visibility = View.GONE
                sendOfferResponseLayout.apply {
                    visibility = View.VISIBLE
                    acceptOfferButton.setOnClickListener {
                        viewModel.sendOfferResponse(accepted = true)
                        sentTv.visibility = View.VISIBLE
                        acceptOfferButton.visibility = View.GONE
                    }
                    rejectOfferButton.setOnClickListener {
                        viewModel.sendOfferResponse(accepted = false)
                    }
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