package com.example.pokemonapp.retrofit

import com.example.pokemonapp.models.Pokemon
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface pokeapiService {
    //whatever name you put in the TextField on the pokemonlistscreen it
    //saves it to pokemonName and adds it to the end of the baseURL
    //to get the data of the pokemon you want.
    @GET("{pokemonName}")
    suspend fun getPokemon(@Path("pokemonName") pokemonName: String): Pokemon
}

object RetrofitInstance {
    val api: pokeapiService by lazy {
        Retrofit.Builder()
            //grabs the base url of the API
            .baseUrl("https://pokeapi.co/api/v2/pokemon/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(pokeapiService::class.java)

    }
}

