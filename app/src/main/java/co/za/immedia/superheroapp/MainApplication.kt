package co.za.immedia.superheroapp

import android.app.Application
import co.za.immedia.search.di.searchViewModelModule
import co.za.immedia.superhero.di.viewSuperheroViewModelModule
import co.za.immedia.superheroapp.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MainApplication)
            modules(
                listOf(
                    searchViewModelModule,
                    viewSuperheroViewModelModule,
                    repositoryModule,
                    persistenceModule,
                    networkingModule
                ) + ModuleLoadHelper.getBuildSpecialModuleList()
            )
        }
    }
}