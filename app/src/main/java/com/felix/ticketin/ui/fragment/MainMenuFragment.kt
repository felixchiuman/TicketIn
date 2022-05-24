package com.felix.ticketin.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.felix.ticketin.R
import com.felix.ticketin.adapter.ComingSoonAdapter
import com.felix.ticketin.adapter.PlayingNowAdapter
import com.felix.ticketin.data.Status
import com.felix.ticketin.databinding.MainMenuFragmentBinding
import com.felix.ticketin.model.comingsoon.MovieComingSoon
import com.felix.ticketin.model.playingnow.MoviePlaying
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainMenuFragment : Fragment() {
    private var _binding: MainMenuFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainMenuViewModel by viewModel()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = MainMenuFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ivFav.setOnClickListener{
            findNavController().navigate(R.id.action_navigation_home_to_favFragment)
        }

        viewModel.playingNow.observe(viewLifecycleOwner){ resource ->
            when (resource.status){
                Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                Status.SUCCESS -> {
                    val adapter = PlayingNowAdapter(object : PlayingNowAdapter.OnClickListener{
                        override fun onClickItem(data: MoviePlaying) {
                            val id = data.id
                            val actionToDetailFragment = MainMenuFragmentDirections.actionNavigationHomeToMovieDetailFragment()
                            actionToDetailFragment.movieId = id.toString().toInt()
                            findNavController().navigate(actionToDetailFragment)
                        }
                    })
                    adapter.submitData(resource.data?.MoviePlaying)
                    binding.rvComingsoon.adapter = adapter
                    binding.progressBar.visibility = View.GONE
                }
                Status.ERROR -> {
                    Toast.makeText(requireContext(), "Error get Data : ${resource.message}", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
        viewModel.getAllPlayingNow()

        viewModel.comingSoon.observe(viewLifecycleOwner){resource ->
            when (resource.status){
                Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                Status.SUCCESS -> {
                    val adapter = ComingSoonAdapter(object : ComingSoonAdapter.OnClickListener{
                        override fun onClickItem(data: MovieComingSoon) {
                            val id = data.id
                            val actionToDetailFragment = MainMenuFragmentDirections.actionNavigationHomeToMovieDetailFragment()
                            actionToDetailFragment.movieId = id.toString().toInt()
                            findNavController().navigate(actionToDetailFragment)
                        }
                    })
                    adapter.submitData(resource.data?.MovieComingSoons)
                    binding.rvComingsoon.adapter = adapter
                    binding.progressBar.visibility = View.GONE
                }
                Status.ERROR -> {
                    Toast.makeText(requireContext(), "Error get Data : ${resource.message}", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
        viewModel.getAllComingSoon()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

