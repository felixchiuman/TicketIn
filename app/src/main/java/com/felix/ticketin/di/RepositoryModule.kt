package com.felix.ticketin.di

import com.felix.ticketin.data.Repository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val repositoryModule = module {
    singleOf(::Repository)
}