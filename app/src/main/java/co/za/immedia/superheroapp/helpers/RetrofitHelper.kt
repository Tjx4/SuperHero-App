package co.za.immedia.superheroapp.helpers

import co.za.immedia.superheroapp.constants.SEARCH_SUPERHERO
import co.za.immedia.superheroapp.models.SuperHero
import retrofit2.http.*


interface RetrofitHelper {
    @GET(SEARCH_SUPERHERO)
    suspend fun getAllUsers(@Query("authorization") token: String): SuperHero?
}