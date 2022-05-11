package com.felix.ticketin.ui.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
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
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pref = DataStoreManager(requireContext())
        viewModel = ViewModelProvider(this, ViewModelFactory(pref))[MainViewModel::class.java]

        setupView()
        setObserver()
    }

    private fun setObserver() {
        viewModel.apply {
            vText.observe(viewLifecycleOwner){result ->
                binding.etSecurity.editText?.setText(result)
            }
        }
    }

    private fun setupView() {
        binding.apply {
            btnEnter.setOnClickListener {
                Log.d("edittext",binding.etSecurity.editText?.text.toString())
                viewModel.saveDataStore(binding.etSecurity.editText?.text.toString())
                Toast.makeText(requireContext(), "Security Pass Created", Toast.LENGTH_SHORT).show()
                it.findNavController().navigate(R.id.action_securityFragment_to_setting)
            }
        }
    }
}