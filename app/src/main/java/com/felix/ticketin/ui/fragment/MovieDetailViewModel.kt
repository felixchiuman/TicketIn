package com.felix.ticketin.ui.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.felix.ticketin.data.Repository
import com.felix.ticketin.data.Resource
import com.felix.ticketin.data.room.FavEntity
import com.felix.ticketin.model.moviedetail.GetAllMovieDetailResponse
import kotlinx.coroutines.launch
import java.lang.Exception


class MovieDetailViewModel(private val repository: Repository) : ViewModel() {
    private val _detailMovie = MutableLiveData<Resource<GetAllMovieDetailResponse>>()
    private val _dataFav = MutableLiveData<FavEntity>()
    val detailMovie: LiveData<Resource<GetAllMovieDetailResponse>>get() = _detailMovie
    val dataFav: LiveData<FavEntity>get() = _dataFav

    fun insertFavorite(favEntity: FavEntity){
        viewModelScope.launch {
            repository.insert(favEntity)
        }
    }

    fun deleteFav(entity: FavEntity){
        viewModelScope.launch {
            repository.delete(entity)
        }
    }

    fun checkFavorite(movieid: Int){
        viewModelScope.launch {
            _dataFav.postValue(repository.getFavorite(movieid))
        }
    }

    fun getAllDetailMovies(movieid: Int){
        viewModelScope.launch {
            _detailMovie.postValue(Resource.loading())
            try {
                _detailMovie.postValue(Resource.success(repository.getDetailMovies(movieid)))
            }catch (exception: Exception){
                _detailMovie.postValue(Resource.error(exception.message ?: "Error Occured"))
            }
        }
    }
}