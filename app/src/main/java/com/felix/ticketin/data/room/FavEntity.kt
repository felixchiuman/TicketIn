package com.felix.ticketin.data.room

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "fav_table")
@Parcelize
data class FavEntity(
    @PrimaryKey
    @ColumnInfo(name = "id") var id: Int?,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "image") var image: String,
):Parcelable
