package co.za.immedia.superhero.di

import co.za.immedia.superhero.ViewSuperheroViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewSuperheroViewModelModule = module {
    viewModel { ViewSuperheroViewModel(androidApplication(), get()) }
}