package com.felix.ticketin.api

import com.felix.ticketin.model.comingsoon.GetAllComingSoonResponse
import com.felix.ticketin.model.moviedetail.GetAllMovieDetailResponse
import com.felix.ticketin.model.playingnow.GetAllPlayingNowIResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("movie/now_playing?api_key=1ff43c35d49d9fd669947e7043b603fc")
    fun getAllPlayingNow(): Call<GetAllPlayingNowIResponse>

    @GET("movie/upcoming?api_key=1ff43c35d49d9fd669947e7043b603fc")
    fun getAllComingSoon(): Call<GetAllComingSoonResponse>

    @GET("movie/{movie_id}")
    fun getMovieDetail(@Path("movie_id")movieid:Int): GetAllMovieDetailResponse
}