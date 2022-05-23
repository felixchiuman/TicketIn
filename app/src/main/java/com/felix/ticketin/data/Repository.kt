package com.felix.ticketin.data

import com.felix.ticketin.data.room.DbHelper
import com.felix.ticketin.data.room.FavEntity
import java.util.concurrent.Flow

class Repository(private val dbHelper: DbHelper) {

    fun getAllFav() = dbHelper.getAllFav()

    suspend fun getFavorite() =dbHelper.getFavorite()

    suspend fun insert(entity: FavEntity) = dbHelper.insert(entity)

    suspend fun delete(entity: FavEntity) = dbHelper.deleteFavorite(entity)
}