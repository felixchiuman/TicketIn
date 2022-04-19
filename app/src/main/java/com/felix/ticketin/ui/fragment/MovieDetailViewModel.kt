package com.felix.ticketin.ui.fragment

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.felix.ticketin.api.ApiClient
import com.felix.ticketin.model.moviedetail.GetAllMovieDetailResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDetailViewModel : ViewModel() {
    private val _detailMovie: MutableLiveData<GetAllMovieDetailResponse> = MutableLiveData()
    val detailMovie: LiveData<GetAllMovieDetailResponse> = _detailMovie

    fun getDetailMovies(movieid: Int){
        ApiClient.instance.getMovieDetail(movieid).enqueue(object : Callback<GetAllMovieDetailResponse> {
            override fun onResponse(
                call: Call<GetAllMovieDetailResponse>,
                response: Response<GetAllMovieDetailResponse>) {

                if (response.code() == 200 || response.code() == 201){
                    Log.d("detil", response.body()?.backdropPath.toString())
                    Log.d("detil2", response.body()?.posterPath.toString())
                    Log.d("detil3", response.body()?.overview.toString())
                    _detailMovie.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<GetAllMovieDetailResponse>, t: Throwable) {}
        })
    }
}