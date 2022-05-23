package com.felix.ticketin.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FavDao {
    @Query("SELECT * FROM fav_table")
    fun getAllFav(): List<FavEntity>

    @Query("SELECT * FROM FAV_TABLE")
    suspend fun getFavorite(): FavEntity

    @Insert(onConflict = REPLACE)
    suspend fun insert(entity: FavEntity)

    @Query("DELETE FROM FAV_TABLE WHERE name= :name")
    suspend fun deleteFavorite(name: String): Int
}