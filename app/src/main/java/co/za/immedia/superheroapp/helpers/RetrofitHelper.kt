package co.za.immedia.superheroapp.helpers

import co.za.immedia.superheroapp.models.SearchResult
import retrofit2.http.*

interface RetrofitHelper {
    @GET
    suspend fun searchSuperHero(@Url url: String): SearchResult?
}