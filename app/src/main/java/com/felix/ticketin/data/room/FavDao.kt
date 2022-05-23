package com.felix.ticketin.data.room

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE

@Dao
interface FavDao {
    @Query("SELECT * FROM fav_table")
    fun getAllFav(): List<FavEntity>

    @Query("SELECT * FROM FAV_TABLE")
    suspend fun getFavorite(): FavEntity

    @Insert(onConflict = REPLACE)
    suspend fun insert(entity: FavEntity)

    @Delete
    suspend fun deleteFavorite(entity: FavEntity)
}