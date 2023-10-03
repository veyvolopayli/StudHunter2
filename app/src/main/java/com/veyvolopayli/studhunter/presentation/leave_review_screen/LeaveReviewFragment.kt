package com.veyvolopayli.studhunter.presentation.leave_review_screen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.veyvolopayli.studhunter.R
import com.veyvolopayli.studhunter.common.Constants
import com.veyvolopayli.studhunter.databinding.FragmentLeaveReviewBinding
import com.veyvolopayli.studhunter.domain.model.WideTask
import com.veyvolopayli.studhunter.presentation.home_screen.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LeaveReviewFragment : BottomSheetDialogFragment(R.layout.fragment_leave_review) {
    private var binding: FragmentLeaveReviewBinding? = null
    private val viewModel: HomeViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentLeaveReviewBinding.bind(view)
        this.binding = binding

        arguments?.getParcelableArrayList("tasks", WideTask::class.java)?.let { tasks ->
            val task = tasks.firstOrNull() ?: return
            with(binding) {
                leaveReviewUserNameTv.text = "${ task.executor.name } ${ task.executor.surname }"
                leaveReviewServiceName.text = task.publication.title
                leaveReviewUserNicknameTv.text = task.executor.username
                leaveReviewUserRatingTv.text = task.executor.rating.toString()
                Glide.with(this@LeaveReviewFragment).load(Constants.getUserAvatarUrl(task.executor.id)).into(leaveReviewUserImageIv)
                leaveReviewButton.setOnClickListener {
                    starsView.value?.let { value ->
                        viewModel.uploadReview(taskId = task.task.id, reviewValue = value, reviewMessage = "Nice")
                        dismiss()
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}