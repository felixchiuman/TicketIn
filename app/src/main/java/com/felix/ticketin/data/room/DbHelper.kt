package com.felix.ticketin.data.room

import java.util.concurrent.Flow

class DbHelper(private val favDao: FavDao) {
    fun getAllFav() = favDao.getAllFav()

    suspend fun getFavorite(name: String) =favDao.getFavorite(name)

    suspend fun insert(entity: FavEntity) = favDao.insert(entity)

    suspend fun delete(entity: FavEntity) = favDao.delete(entity)
}