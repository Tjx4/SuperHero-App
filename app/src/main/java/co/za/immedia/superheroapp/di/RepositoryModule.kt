package co.za.immedia.superheroapp.di

import co.za.immedia.repositories.SuperheroesRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { SuperheroesRepository(get(), get()) }
}