package com.veyvolopayli.studhunter.presentation.home_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.veyvolopayli.studhunter.R
import com.veyvolopayli.studhunter.databinding.FragmentHomeBinding
import com.veyvolopayli.studhunter.presentation.custom_views.LeaveReviewNotificationView
import com.veyvolopayli.studhunter.presentation.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var binding: FragmentHomeBinding? = null
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        this.binding = binding

//        val leaveReviewNotificationView = LeaveReviewNotificationView(requireContext())
//        leaveReviewNotificationView.startAnim()

        binding.leaveReviewView.startAnim()

        binding.rvHome.layoutManager = GridLayoutManager(requireContext(), 2)

        viewModel.state.observe(viewLifecycleOwner) { state ->
            val publicationsAdapter = HomeRvAdapter()
            publicationsAdapter.setData(state.publications)
            binding.rvHome.adapter = publicationsAdapter

            publicationsAdapter.onItemClick = { id ->
                val bundle = Bundle()
                bundle.putString("id", id)

                findNavController().navigate(R.id.action_homeFragment_to_publicationFragment, bundle)
            }

            binding.refreshLayout.isRefreshing = false
        }

        viewModel.event.observe(viewLifecycleOwner) { homeEvent ->
            when (homeEvent) {
                is HomeEvent.Loading -> {
                    binding.loadingLayout.root.visibility = View.VISIBLE
                }

                is HomeEvent.Success -> {
                    binding.loadingLayout.root.visibility = View.GONE
                }

                is HomeEvent.Error -> {
                    binding.loadingLayout.root.visibility = View.VISIBLE
                }
            }
        }

        binding.homeSearchTv.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_searchFragment)
        }

        binding.refreshLayout.setOnRefreshListener {
            viewModel.fetchPublications()
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}