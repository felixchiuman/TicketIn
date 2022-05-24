package com.felix.ticketin.ui.fragment

import android.app.ProgressDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.felix.ticketin.R
import com.felix.ticketin.adapter.ComingSoonAdapter
import com.felix.ticketin.adapter.PlayingNowAdapter
import com.felix.ticketin.data.Status
import com.felix.ticketin.data.service.ApiClient
import com.felix.ticketin.databinding.MainMenuFragmentBinding
import com.felix.ticketin.model.comingsoon.GetAllComingSoonResponse
import com.felix.ticketin.model.comingsoon.ResultComingSoon
import com.felix.ticketin.model.playingnow.GetAllPlayingNowIResponse
import com.felix.ticketin.model.playingnow.Result
import org.koin.androidx.viewmodel.ext.android.viewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainMenuFragment : Fragment() {
    private var _binding: MainMenuFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainMenuViewModel by viewModel()
    val progressDialog = ProgressDialog(requireContext())


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = MainMenuFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvUser.setOnClickListener {
            viewModel.uncensored()
        }

        binding.ivFav.setOnClickListener{
            findNavController().navigate(R.id.action_navigation_home_to_favFragment)
        }

        viewModel.email.observe(viewLifecycleOwner,{
            binding.tvUser.text = it
        })

        viewModel.plaingNow.observe(viewLifecycleOwner){resource ->
            when (resource.status){
                Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                Status.SUCCESS -> {
                    val adapter = PlayingNowAdapter(object : PlayingNowAdapter.OnClickListener{
                        override fun onClickItem(data: Result) {
                            val id = data.id
                            val actionToDetailFragment = MainMenuFragmentDirections.actionNavigationHomeToMovieDetailFragment()
                            actionToDetailFragment.movieId = id.toString().toInt()
                            findNavController().navigate(actionToDetailFragment)
                        }
                    })
                    adapter.submitData(resource.data)
                    binding.rvComingsoon.adapter = adapter
                    binding.progressBar.visibility = View.GONE
                }
                Status.ERROR -> {
                    Toast.makeText(requireContext(), "Error get Data : ${resource.message}", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }

        viewModel.comingSoon.observe(viewLifecycleOwner){resource ->
            when (resource.status){
                Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                Status.SUCCESS -> {
                    val adapter = ComingSoonAdapter(object : ComingSoonAdapter.OnClickListener{
                        override fun onClickItem(data: ResultComingSoon) {
                            val id = data.id
                            val actionToDetailFragment = MainMenuFragmentDirections.actionNavigationHomeToMovieDetailFragment()
                            actionToDetailFragment.movieId = id.toString().toInt()
                            findNavController().navigate(actionToDetailFragment)
                        }
                    })
                    adapter.submitData(resource.data)
                    binding.rvComingsoon.adapter = adapter
                    binding.progressBar.visibility = View.GONE
                }
                Status.ERROR -> {
                    Toast.makeText(requireContext(), "Error get Data : ${resource.message}", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

