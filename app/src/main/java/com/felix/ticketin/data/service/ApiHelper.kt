package com.felix.ticketin.data.service

class ApiHelper(val apiService: ApiService) {

    suspend fun getAllPlayingNow() = apiService.getAllPlayingNow()

    suspend fun getAllComingSoon() = apiService.getAllComingSoon()

    suspend fun getMovieDetail(movieId : Int) = apiService.getMovieDetail(movieId)
}
