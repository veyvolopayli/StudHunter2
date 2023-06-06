package com.veyvolopayli.studhunter.presentation.home_screen

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.veyvolopayli.studhunter.databinding.FragmentHomeBinding
import com.veyvolopayli.studhunter.presentation.home_screen.delete.TempHomeRvAdapter
import com.veyvolopayli.studhunter.presentation.home_screen.delete.TempMainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var binding: FragmentHomeBinding? = null
    private val viewModel: TempMainViewModel by viewModels()

    init {
        Log.e("init", "reached")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        this.binding = binding

        Log.e("onCreateView", "reached")

        binding.rvHome.layoutManager = GridLayoutManager(requireContext(), 2)

        viewModel.state.observe(viewLifecycleOwner) { users ->
//            Toast.makeText(requireContext(), "t", Toast.LENGTH_SHORT).show()
//            val publications =
            Log.e("observe", "reached")
            val publicationsAdapter = TempHomeRvAdapter()
            publicationsAdapter.setData(users)
            binding.rvHome.adapter = publicationsAdapter
        }

        /*viewModel.event.observe(viewLifecycleOwner) { homeEvent ->
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
        }*/

        return binding.root
    }

    /*override fun onPause() {
        super.onPause()
        val state = binding?.rvHome?.layoutManager?.onSaveInstanceState() ?: return
        viewModel.saveRecyclerState(state)
    }

    override fun onResume() {
        super.onResume()
        binding?.rvHome?.layoutManager?.onRestoreInstanceState(viewModel.recyclerState) ?: return
    }*/

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}