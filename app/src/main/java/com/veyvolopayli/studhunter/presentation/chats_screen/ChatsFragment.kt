package com.veyvolopayli.studhunter.presentation.chats_screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.veyvolopayli.studhunter.R
import com.veyvolopayli.studhunter.databinding.FragmentChatsBinding
import com.veyvolopayli.studhunter.presentation.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChatsFragment : Fragment() {
    private var binding: FragmentChatsBinding? = null
    private val vm: ChatsViewModel by viewModels()
    private val mainVm: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentChatsBinding.inflate(layoutInflater, container, false)
        this.binding = binding

        val chatsAdapter = ChatsAdapter()

        chatsAdapter.onItemClick = { chat ->
            val bundle = bundleOf("chat_id" to chat.chatId, "seller_id" to chat.sellerId)
            findNavController().navigate(R.id.action_chatsFragment_to_userChatFragment2, bundle)
//            mainVm.hideBottomBar()
        }

        binding.swipeLayout.apply {
            setOnRefreshListener {
                vm.getChats()
                isRefreshing = false
            }
        }

        binding.chatsRv.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = chatsAdapter
        }

        vm.chats.observe(viewLifecycleOwner) { chats ->
            chatsAdapter.setData(chats)
        }

        vm.toastEvent.observe(viewLifecycleOwner) { message ->
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun onResume() {
        super.onResume()
    }

}