package co.za.immedia.superheroapp.helpers

import co.za.immedia.superheroapp.enums.Hosts
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object API {
    var retrofit: RetrofitHelper

    init {
        val builder = Retrofit.Builder()
            .baseUrl(Hosts.LiveHost.url)
            .addConverterFactory(GsonConverterFactory.create())
        val retrofit = builder.build()

        this.retrofit = retrofit.create(RetrofitHelper::class.java)
    }
}