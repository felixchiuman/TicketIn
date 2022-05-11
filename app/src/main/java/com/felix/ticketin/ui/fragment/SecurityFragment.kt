package com.felix.ticketin.ui.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.felix.ticketin.DataStoreManager
import com.felix.ticketin.R
import com.felix.ticketin.databinding.FragmentSecurityBinding
import com.felix.ticketin.ui.MainViewModel
import com.felix.ticketin.ui.ViewModelFactory

class SecurityFragment : Fragment() {
    private var _binding: FragmentSecurityBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MainViewModel
    private lateinit var pref: DataStoreManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSecurityBinding.inflate(inflater, container, false)
        return binding.root

        pref = DataStoreManager(requireContext())
        viewModel = ViewModelProvider(this, ViewModelFactory(pref))[MainViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()
        setObserver()
    }

    private fun setObserver() {
        viewModel.apply {
            vText.observe(viewLifecycleOwner){result ->
                Log.d("edittext",binding.etSecurity.editText?.text.toString())
                binding.etSecurity.editText?.setText(result)
            }
        }
    }

    private fun setupView() {
        binding.apply {
            btnEnter.setOnClickListener {
                viewModel.saveDataStore(binding.etSecurity.editText?.text.toString())
            }
        }
    }
}