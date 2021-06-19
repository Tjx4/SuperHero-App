package co.za.immedia.herorating

import android.app.Application
import co.za.immedia.herorating.di.*
import org.koin.core.context.startKoin

class RatingApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(
                listOf(
                    ratingViewModelModule,
                    ratingRepositoryModule,
                    ratingPersistenceModule,
                    ratingNetworkingModule
                ) + ModuleLoadHelper.getBuildSpecialModuleList()
            )
        }
    }
}