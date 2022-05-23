package com.felix.ticketin.di

import androidx.room.Room
import com.felix.ticketin.data.room.DbHelper
import com.felix.ticketin.data.room.FavDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room
            .databaseBuilder(
                androidContext().applicationContext,
                FavDatabase::class.java,
                "database"
            )
            .build()
    }
    single {
        get<FavDatabase>().favDao()
    }
    singleOf(::DbHelper)
}