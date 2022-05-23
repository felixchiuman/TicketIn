package com.felix.ticketin.data.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [FavEntity::class], version = 1)
abstract class FavDatabase: RoomDatabase() {
    abstract fun favDao(): FavDao
}