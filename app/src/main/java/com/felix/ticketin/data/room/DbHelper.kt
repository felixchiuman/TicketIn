package com.felix.ticketin.data.room

import java.util.concurrent.Flow

class DbHelper(private val favDao: FavDao) {
    fun getAllFav() = favDao.getAllFav()

    suspend fun getFavorite() =favDao.getFavorite()

    suspend fun insert(entity: FavEntity) = favDao.insert(entity)

    suspend fun deleteFavorite(name: String) = favDao.deleteFavorite(name)
}