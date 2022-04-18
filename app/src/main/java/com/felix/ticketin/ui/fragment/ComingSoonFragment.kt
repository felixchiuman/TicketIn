package com.felix.ticketin.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.felix.ticketin.R
import com.felix.ticketin.databinding.FragmentComingSoonBinding


class ComingSoonFragment : Fragment() {

    private var _binding: FragmentComingSoonBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentComingSoonBinding.inflate(inflater, container, false)
        return  binding.root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}