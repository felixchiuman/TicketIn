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

    @Query("SELECT * FROM FAV_TABLE WHERE name= :name")
    suspend fun getFavorite(name: String): FavEntity

    @Insert(onConflict = REPLACE)
    suspend fun insert(entity: FavEntity)

    @Delete
    suspend fun delete(entity: FavEntity): Int
}