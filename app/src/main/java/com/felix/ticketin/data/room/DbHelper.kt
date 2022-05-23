package com.felix.ticketin.data.room

import java.util.concurrent.Flow

class DbHelper(private val favDao: FavDao) {
    fun getAllFav() = favDao.getAllFav()

    suspend fun getFavorite(id: Int) =favDao.getFavorite(id)

    suspend fun insert(entity: FavEntity) = favDao.insert(entity)

    suspend fun deleteFavorite(entity: FavEntity) = favDao.deleteFavorite(entity)
}