package com.veyvolopayli.studhunter.presentation.leave_review_screen

import android.os.Bundle
import android.view.View
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.veyvolopayli.studhunter.R
import com.veyvolopayli.studhunter.databinding.FragmentLeaveReviewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LeaveReviewFragment : BottomSheetDialogFragment(R.layout.fragment_leave_review) {
    private var binding: FragmentLeaveReviewBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentLeaveReviewBinding.bind(view)
        this.binding = binding


    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}