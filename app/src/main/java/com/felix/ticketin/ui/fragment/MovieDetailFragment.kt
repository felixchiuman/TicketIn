package com.felix.ticketin.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.felix.ticketin.R
import com.felix.ticketin.data.room.FavEntity
import com.felix.ticketin.databinding.MovieDetailFragmentBinding
import com.squareup.picasso.Picasso
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieDetailFragment : Fragment() {
    private var _binding: MovieDetailFragmentBinding? = null
    private val binding get() = _binding!!
    private val IMAGE_BASE ="https://image.tmdb.org/t/p/w500/"
    private val viewModel: MovieDetailViewModel by viewModel()
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

        val movieId = args.movieId
        var title = "default title"
        var name = "default name"
        var image = "default image"

        viewModel.detailMovie.observe(viewLifecycleOwner){
            title = it.title
            Picasso.get().load(IMAGE_BASE+it.backdropPath).fit().into(binding.ivBackdrop)
            Picasso.get().load(IMAGE_BASE+it.posterPath).fit().into(binding.ivPoster)
            binding.tvDetailTitle.text = it.title
            binding.tvDescDetail.text = it.overview

            name = it.title
            image = it.posterPath
        }
        var checkFav = false
        viewModel.checkFavorite(name)

        viewModel.dataFav.observe(viewLifecycleOwner){
            if (it != null){
                if (it.name.contains(title)){
                    binding.ivFav.setImageResource(R.drawable.ic_baseline_favorite_36)
                    checkFav = true
                }
            }
        }

        binding.ivFav.setOnClickListener {
            if (checkFav){
                viewModel.deleteFav(FavEntity(null, name,image))
                binding.ivFav.setImageResource(R.drawable.ic_baseline_favorite_border_36)
            }else{
                viewModel.insertFavorite(FavEntity(null, name, image))
                binding.ivFav.setImageResource(R.drawable.ic_baseline_favorite_36)
            }
            viewModel.checkFavorite(name)
        }



        viewModel.getDetailMovies(movieId)

        binding.ivBack.setOnClickListener {
            findNavController().navigate(R.id.action_movieDetailFragment_to_navigation_home)
        }

    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}