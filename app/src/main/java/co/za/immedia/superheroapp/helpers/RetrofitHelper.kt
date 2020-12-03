package co.za.immedia.superheroapp.helpers

import co.za.immedia.superheroapp.constants.GET_SUPERHERO
import co.za.immedia.superheroapp.models.SuperHero
import retrofit2.http.*


interface RetrofitHelper {
    @GET(GET_SUPERHERO)
    suspend fun getAllUsers(@Query("authorization") token: String): SuperHero?
}