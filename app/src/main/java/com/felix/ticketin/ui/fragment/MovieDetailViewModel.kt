package com.felix.ticketin.ui.fragment

import android.util.Log
import android.widget.Toast
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