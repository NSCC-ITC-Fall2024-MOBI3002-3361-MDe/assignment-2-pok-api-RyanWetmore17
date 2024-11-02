package com.example.pokemonapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pokemonapp.components.PokemonSearchScreen
import com.example.pokemonapp.models.PokemonViewModel

class MainActivity : ComponentActivity() {

//    @SuppressLint("ServiceCast", "MissingPermission")
//    fun isNetworkAvailable(context: Context): Boolean {
//        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//        val network = connectivityManager.activeNetwork ?: return false
//        val networkCapabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
//        return networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel: PokemonViewModel = viewModel()
//            if (isNetworkAvailable(this)) {
                PokemonSearchScreen(viewModel)
//            } else {
//                Toast.makeText(this, "No internet connection", Toast.LENGTH_LONG).show()
//            }
//
//            val viewModel: PokemonViewModel = viewModel()
//            PokemonListScreen(viewModel)
        }
    }
}