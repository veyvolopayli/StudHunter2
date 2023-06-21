package com.veyvolopayli.studhunter.presentation.create_publication_screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.veyvolopayli.studhunter.R
import com.veyvolopayli.studhunter.databinding.FragmentCreatePublicationBinding

class CreatePublicationFragment : Fragment() {

    private var binding: FragmentCreatePublicationBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentCreatePublicationBinding.inflate(layoutInflater, container, false)
        this.binding = binding



        return inflater.inflate(R.layout.fragment_create_publication, container, false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}