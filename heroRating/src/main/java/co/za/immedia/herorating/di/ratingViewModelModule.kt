package co.za.immedia.herorating.di

import co.za.immedia.herorating.RatingViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val ratingViewModelModule = module {
    viewModel { RatingViewModel(androidApplication(), get()) }
}
