package co.za.immedia.herorating.di

import co.za.immedia.repositories.SuperheroesRepository
import org.koin.dsl.module

val ratingRepositoryModule = module {
    single { SuperheroesRepository(get(), get()) }
}