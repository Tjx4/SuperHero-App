package co.za.immedia.superheroapp.helpers

import co.za.immedia.superheroapp.models.SuperHero
import retrofit2.http.*

interface RetrofitHelper {
    @GET
    suspend fun searchSuperHero(@Url url: String): List<SuperHero>?
}