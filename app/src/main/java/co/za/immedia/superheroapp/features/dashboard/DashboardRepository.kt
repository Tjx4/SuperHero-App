package co.za.immedia.superheroapp.features.dashboard

import co.za.immedia.superheroapp.helpers.RetrofitHelper
import co.za.immedia.superheroapp.models.SearchResult

class DashboardRepository(var retrofit: RetrofitHelper) {

    suspend fun searchForSuperHero(url: String): SearchResult?{
        return try {
            retrofit.searchSuperHero(url)
        }
        catch (ex: Exception){
            val dfd = ex
            null
        }
    }
}