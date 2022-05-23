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
        var name = "default name"
        var image = "default image"

        viewModel.getDetailMovies(movieId)
        viewModel.checkFavorite(movieId)

        viewModel.detailMovie.observe(viewLifecycleOwner){data ->
            name = data.title
            image = data.posterPath
            Picasso.get().load(IMAGE_BASE+data.backdropPath).fit().into(binding.ivBackdrop)
            Picasso.get().load(IMAGE_BASE+data.posterPath).fit().into(binding.ivPoster)
            binding.tvDetailTitle.text = data.title
            binding.tvDescDetail.text = data.overview

            viewModel.dataFav.observe(viewLifecycleOwner){
                if (it != null){
                    if (it.id == data.id){
                        binding.ivFav.setImageResource(R.drawable.ic_baseline_favorite_36)
                    }
                }
            }
        }

        binding.ivFav.setOnClickListener {
            addFavorite(movieId,name,image)
        }

        binding.ivBack.setOnClickListener {
            findNavController().navigate(R.id.action_movieDetailFragment_to_navigation_home)
        }
    }

    private fun addFavorite(id : Int?, name: String, image: String) {
        var checkFav = false

        viewModel.dataFav.observe(viewLifecycleOwner){
            if (it != null){
                if (it.id == id){
//                    binding.ivFav.setImageResource(R.drawable.ic_baseline_favorite_36)
                    checkFav = true
                }
            }
        }
            if (checkFav){
                binding.ivFav.setImageResource(R.drawable.ic_baseline_favorite_border_36)
                viewModel.deleteFav(FavEntity(id, name, image))
            }else{
                binding.ivFav.setImageResource(R.drawable.ic_baseline_favorite_36)
                viewModel.insertFavorite(FavEntity(id, name, image))
            }
            viewModel.checkFavorite(id.toString().toInt())
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}