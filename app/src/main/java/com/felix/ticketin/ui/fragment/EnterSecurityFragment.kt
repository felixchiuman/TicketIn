package com.felix.ticketin.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.felix.ticketin.DataStoreManager
import com.felix.ticketin.R
import com.felix.ticketin.databinding.FragmentEnterSecurityBinding
import com.felix.ticketin.databinding.FragmentSecurityBinding
import com.felix.ticketin.ui.MainViewModel
import com.felix.ticketin.ui.ViewModelFactory

class EnterSecurityFragment : Fragment() {
    private var _binding: FragmentEnterSecurityBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MainViewModel
    private lateinit var pref: DataStoreManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentEnterSecurityBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pref = DataStoreManager(requireContext())
        viewModel = ViewModelProvider(this, ViewModelFactory(pref))[MainViewModel::class.java]

        setObserver()
    }

    private fun setObserver() {
        viewModel.apply {
            getDataStore().observe(viewLifecycleOwner){result ->
                if (result.equals("")){
                    findNavController().navigate(R.id.action_enterSecurityFragment_to_profileFragment)
                }else{
                    binding.btnEnter.setOnClickListener {
                        Log.d("resultan", result.toString())
                        Log.d("edittext", binding.etSecurity.editText?.text.toString())
                        if (binding.etSecurity.editText?.text.toString().equals(result)){
                            Toast.makeText(requireContext(), "Access Granted", Toast.LENGTH_SHORT).show()
                            it.findNavController().navigate(R.id.action_enterSecurityFragment_to_profileFragment)
                        }
                        else if (binding.etSecurity.editText?.text.toString().equals("")){
                            Toast.makeText(requireContext(), "Input Error", Toast.LENGTH_SHORT).show()
                        }
                        else{
                            Toast.makeText(requireContext(), "Access declined", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }
}