package com.example.pokemonapp.models

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemonapp.retrofit.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class PokemonViewModel : ViewModel() {
    private val _pokemon = MutableStateFlow<Pokemon?>(null)
    val pokemon: StateFlow<Pokemon?> = _pokemon
//
//    init {
//        fetchPokemon()
//    }

//    private fun fetchPokemon() {
//        viewModelScope.launch{
//            try {
//                val response = RetrofitIAnstance.api.getPokemon()
////                val _pokemon = RetrofitInstance.api.getPokemon()
//                _pokemon.value = response.results
//            }catch (e: Exception) {
//                //Handling the error
//            }
//        }
//    }
//}

    fun fetchPokemon(pokemonName: String) {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getPokemon(pokemonName)
                Log.d("ViewModel","Pokemon: $response")
                _pokemon.value = response  // Update the state flow with the fetched data
            } catch (e: IOException) {
                // Handle the error
                Log.e("ViewModel","Network error: ${e.message}",e)
            } catch (e: HttpException){
                Log.e("ViewModel","Http error: ${e.message}",e)
            } catch (e: Exception){
                Log.e("ViewModel","Pokemon Fetch error: ${e.message}",e)

            }
        }
    }
}