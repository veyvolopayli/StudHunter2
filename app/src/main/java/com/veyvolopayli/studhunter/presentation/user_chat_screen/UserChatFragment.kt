package com.veyvolopayli.studhunter.presentation.user_chat_screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
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

        viewModel.chatMessagesState.observe(viewLifecycleOwner) { messages ->
            
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}