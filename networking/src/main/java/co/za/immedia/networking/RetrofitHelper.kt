package co.za.immedia.networking

import co.za.immedia.commons.models.Appearance
import co.za.immedia.commons.models.Connections
import co.za.immedia.commons.models.SearchResult
import co.za.immedia.commons.models.Work
import retrofit2.http.*

interface RetrofitHelper {
    @GET
    suspend fun searchSuperHero(@Url url: String): SearchResult?

    @GET
    suspend fun getHeroAppearance(@Url url: String): Appearance?

    @GET
    suspend fun getHeroWork(@Url url: String): Work?

    @GET
    suspend fun getHeroConnections(@Url url: String): Connections?
}