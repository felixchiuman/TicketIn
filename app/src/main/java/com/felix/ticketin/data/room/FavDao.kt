package com.felix.ticketin.data.room

import android.media.Image
import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE

@Dao
interface FavDao {
    @Query("SELECT * FROM fav_table")
    fun getAllFav(): List<FavEntity>

    @Query("SELECT * FROM fav_table WHERE id= :id LIMIT 1")
    suspend fun getFavorite(id: Int): FavEntity

    @Insert(onConflict = REPLACE)
    suspend fun insert(entity: FavEntity)

    @Delete
    suspend fun deleteFavorite(entity: FavEntity)
}