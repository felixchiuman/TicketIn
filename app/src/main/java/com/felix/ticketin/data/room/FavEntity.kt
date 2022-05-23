package com.felix.ticketin.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "fav_table")
data class FavEntity(
    @PrimaryKey
    @ColumnInfo(name = "id") var id: Int?,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "image") var image: String,
)
