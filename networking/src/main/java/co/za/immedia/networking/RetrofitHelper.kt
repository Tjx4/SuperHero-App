package co.za.immedia.networking

import co.za.immedia.commons.models.Appearance
import co.za.immedia.commons.models.SearchResult
import retrofit2.http.*

interface RetrofitHelper {
    @GET
    suspend fun searchSuperHero(@Url url: String): SearchResult?

    @GET
    suspend fun getHeroAppearance(@Url url: String): Appearance?
}