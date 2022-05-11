package com.felix.ticketin.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.felix.ticketin.R
import com.felix.ticketin.databinding.FragmentSettingBinding
import com.google.firebase.auth.FirebaseAuth


class Setting : Fragment() {
    private var _binding: FragmentSettingBinding? = null
    private val binding get() = _binding!!
    lateinit var firebaseAuth: FirebaseAuth


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

        firebaseAuth = FirebaseAuth.getInstance()

        binding.tvSecurity.setOnClickListener {
            it.findNavController().navigate(R.id.action_setting_to_securityFragment)
        }

        binding.btnSignout.setOnClickListener {
            firebaseAuth.signOut()
            it.findNavController().navigate(R.id.action_setting_to_login_Activity)
        }
    }
}