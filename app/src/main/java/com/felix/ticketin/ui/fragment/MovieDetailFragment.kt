package com.felix.ticketin.ui.fragment

import android.content.Intent
import android.graphics.Movie
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.felix.ticketin.R
import com.felix.ticketin.databinding.MovieDetailFragmentBinding
import com.felix.ticketin.model.moviedetail.GetAllMovieDetailResponse
import com.squareup.picasso.Picasso

class MovieDetailFragment : Fragment() {
    private var _binding: MovieDetailFragmentBinding? = null
    private val binding get() = _binding!!
    private val IMAGE_BASE ="https://image.tmdb.org/t/p/w500/"
    private lateinit var viewModel: MovieDetailViewModel
    val args: MovieDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = MovieDetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(MovieDetailViewModel::class.java)

        val movieId = args.movieId

        viewModel.detailMovie.observe(viewLifecycleOwner){
            Picasso.get().load(IMAGE_BASE+it.backdropPath).fit().into(binding.ivBackdrop)
            Picasso.get().load(IMAGE_BASE+it.posterPath).fit().into(binding.ivPoster)
            binding.tvDetailTitle.text = it.title
            binding.tvDescDetail.text = it.overview
        }
        viewModel.getDetailMovies(movieId)

        binding.ivBack.setOnClickListener {
            findNavController().navigate(R.id.action_movieDetailFragment_to_navigation_home)
        }
    }
}