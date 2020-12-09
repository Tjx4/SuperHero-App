package co.za.immedia.mylib

import co.za.immedia.commons.models.SearchResult
import retrofit2.http.*

interface RetrofitHelper {
    @GET
    suspend fun searchSuperHero(@Url url: String): co.za.immedia.commons.models.SearchResult?
}