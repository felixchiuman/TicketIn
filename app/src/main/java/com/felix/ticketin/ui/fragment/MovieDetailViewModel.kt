package com.felix.ticketin.ui.fragment

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.felix.ticketin.service.ApiClient
import com.felix.ticketin.model.moviedetail.GetAllMovieDetailResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDetailViewModel(application: Application) : AndroidViewModel(application) {
    private val _detailMovie: MutableLiveData<GetAllMovieDetailResponse> = MutableLiveData()
    val detailMovie: LiveData<GetAllMovieDetailResponse> = _detailMovie

    fun getDetailMovies(movieid: Int){
        ApiClient.getInstance(getApplication()).getMovieDetail(movieid).enqueue(object : Callback<GetAllMovieDetailResponse> {
            override fun onResponse(
                call: Call<GetAllMovieDetailResponse>,
                response: Response<GetAllMovieDetailResponse>) {

                Log.d("detil", response.code().toString())

                if (response.isSuccessful){
                    _detailMovie.postValue(response.body())
                }else{
                }
            }

            override fun onFailure(call: Call<GetAllMovieDetailResponse>, t: Throwable) {}
        })
    }
}