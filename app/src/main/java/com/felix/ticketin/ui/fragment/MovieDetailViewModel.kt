package com.felix.ticketin.ui.fragment

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.felix.ticketin.data.Repository
import com.felix.ticketin.data.room.FavEntity
import com.felix.ticketin.data.service.ApiClient
import com.felix.ticketin.model.moviedetail.GetAllMovieDetailResponse
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDetailViewModel(private val repository: Repository) : ViewModel() {
    private val _detailMovie: MutableLiveData<GetAllMovieDetailResponse> = MutableLiveData()
    private val _dataFav = MutableLiveData<FavEntity>()
    val detailMovie: LiveData<GetAllMovieDetailResponse> = _detailMovie
    val dataFav: LiveData<FavEntity>get() = _dataFav

    fun insertFavorite(favEntity: FavEntity){
        viewModelScope.launch {
            repository.insert(favEntity)
        }
    }

    fun deleteFav(name: String){
        viewModelScope.launch {
            repository.delete(name)
        }
    }

    fun checkFavorite(){
        viewModelScope.launch {
            _dataFav.postValue(repository.getFavorite())
        }
    }

    fun getDetailMovies(movieid: Int){
        ApiClient.instance.getMovieDetail(movieid).enqueue(object : Callback<GetAllMovieDetailResponse> {
            override fun onResponse(
                call: Call<GetAllMovieDetailResponse>,
                response: Response<GetAllMovieDetailResponse>) {

                Log.d("detil", response.code().toString())

                if (response.isSuccessful){
                    _detailMovie.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<GetAllMovieDetailResponse>, t: Throwable) {}
        })
    }
}