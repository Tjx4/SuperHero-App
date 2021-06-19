package co.za.immedia.herorating.di

import co.za.immedia.networking.API
import co.za.immedia.persistence.room.SuperheroDB
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val ratingNetworkingModule = module {
    single { API.retrofit }
}

val ratingPersistenceModule = module {
    single { SuperheroDB.getInstance(androidApplication())}
}