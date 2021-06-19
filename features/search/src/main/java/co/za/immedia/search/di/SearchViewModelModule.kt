package co.za.immedia.search.di

import co.za.immedia.search.SearchViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val searchViewModelModule = module {
    viewModel { SearchViewModel(androidApplication(), get()) }
}