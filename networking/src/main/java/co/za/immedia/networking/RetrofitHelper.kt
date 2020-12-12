package co.za.immedia.networking

import co.za.immedia.commons.models.SearchResult
import retrofit2.http.*

interface RetrofitHelper {
    @GET
    suspend fun searchSuperHero(@Url url: String): SearchResult?

    @GET
    suspend fun getHeroWor(@Url url: String): SearchResult?
}