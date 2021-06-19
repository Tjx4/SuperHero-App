package co.za.immedia.superheroapp.di

import co.za.immedia.search.SearchViewModel
import co.za.immedia.superhero.ViewSuperheroViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { SearchViewModel(androidApplication(), get()) }
    viewModel { ViewSuperheroViewModel(androidApplication(), get()) }
    //viewModel { RatingViewModel(androidApplication(), get()) }
}
