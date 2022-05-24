package com.felix.ticketin.data.service

import com.felix.ticketin.model.comingsoon.GetAllComingSoonResponse
import com.felix.ticketin.model.moviedetail.GetAllMovieDetailResponse
import com.felix.ticketin.model.playingnow.GetAllPlayingNowIResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("movie/now_playing")
    suspend fun getAllPlayingNow(): GetAllPlayingNowIResponse

    @GET("movie/upcoming")
    suspend fun getAllComingSoon(): GetAllComingSoonResponse

    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(@Path("movie_id")movieid:Int): GetAllMovieDetailResponse
}