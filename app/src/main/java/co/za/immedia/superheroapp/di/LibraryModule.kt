package co.za.immedia.superheroapp.di

import co.za.immedia.networking.API
import co.za.immedia.persistence.room.SuperheroDB
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val networkingModule = module {
    single { API.retrofit }
}

val persistenceModule = module {
    single { SuperheroDB.getInstance(androidApplication())}
}