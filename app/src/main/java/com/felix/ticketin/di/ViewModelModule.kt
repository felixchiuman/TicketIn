package com.felix.ticketin.di

import com.felix.ticketin.ui.fragment.FavViewModel
import com.felix.ticketin.ui.fragment.MainMenuViewModel
import com.felix.ticketin.ui.fragment.MovieDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::FavViewModel)
    viewModelOf(::MovieDetailViewModel)
    viewModelOf(::MainMenuViewModel)
}