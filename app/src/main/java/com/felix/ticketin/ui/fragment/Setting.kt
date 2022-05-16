package com.felix.ticketin.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.felix.ticketin.DataStoreManager
import com.felix.ticketin.R
import com.felix.ticketin.databinding.FragmentSettingBinding
import com.felix.ticketin.ui.MainViewModel
import com.felix.ticketin.ui.ViewModelFactory
import com.google.firebase.auth.FirebaseAuth


class Setting : Fragment() {
    private var _binding: FragmentSettingBinding? = null
    private val binding get() = _binding!!
    lateinit var firebaseAuth: FirebaseAuth
    private lateinit var viewModel: MainViewModel
    private lateinit var pref: DataStoreManager


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSettingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pref = DataStoreManager(requireContext())
        viewModel = ViewModelProvider(this, ViewModelFactory(pref))[MainViewModel::class.java]

        firebaseAuth = FirebaseAuth.getInstance()

        binding.tvProfile.setOnClickListener {
            it.findNavController().navigate(R.id.action_setting_to_enterSecurityFragment)
        }

        binding.tvSecurity.setOnClickListener {
            it.findNavController().navigate(R.id.action_setting_to_securityFragment)
        }

        binding.btnSignout.setOnClickListener {
            firebaseAuth.signOut()
            it.findNavController().navigate(R.id.action_setting_to_login_Activity)
        }
    }
}