package com.felix.ticketin.data

import com.felix.ticketin.data.room.DbHelper
import com.felix.ticketin.data.room.FavEntity
import com.felix.ticketin.data.service.ApiHelper
import java.util.concurrent.Flow

class Repository(private val dbHelper: DbHelper, private val apiHelper: ApiHelper) {

    fun getAllFav() = dbHelper.getAllFav()

    suspend fun getFavorite(id: Int) =dbHelper.getFavorite(id)

    suspend fun insert(entity: FavEntity) = dbHelper.insert(entity)

    suspend fun delete(entity: FavEntity) = dbHelper.deleteFavorite(entity)

    suspend fun getDetailMovies(movieId : Int) = apiHelper.getMovieDetail(movieId)

    suspend fun getAllPlayingNow() = apiHelper.getAllPlayingNow()

    suspend fun getAllComingSoon() = apiHelper.getAllComingSoon()
}