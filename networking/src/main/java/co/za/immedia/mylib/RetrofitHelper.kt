package co.za.immedia.mylib

import co.za.immedia.models.SearchResult
import retrofit2.http.*

interface RetrofitHelper {
    @GET
    suspend fun searchSuperHero(@Url url: String): SearchResult?
}