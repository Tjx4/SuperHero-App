package co.za.immedia.superheroapp.features.dashboard

import co.za.immedia.superheroapp.helpers.RetrofitHelper
import co.za.immedia.superheroapp.models.SuperHero

class DashboardRepository(var retrofit: RetrofitHelper) {

    suspend fun searchForSuperHero(url: String): List<SuperHero>?{
        return try {
            retrofit.searchSuperHero(url)
        }
        catch (ex: Exception){
            null
        }
    }
}