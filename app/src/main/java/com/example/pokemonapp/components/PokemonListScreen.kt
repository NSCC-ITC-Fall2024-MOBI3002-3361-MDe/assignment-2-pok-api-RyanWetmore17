package com.example.pokemonapp.components

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.example.pokemonapp.models.PokemonViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonSearchScreen(pokemonViewModel: PokemonViewModel) {
    val pokemon by pokemonViewModel.pokemon.collectAsState()
    var pokemonName by remember { mutableStateOf("") } // State for the text field input

    Scaffold(
        topBar = {
            //adds the title to the top of the app
            TopAppBar(title = { Text("Pokemon Search App") })
        }
    ) {
        Column(
            modifier = Modifier.padding(top = 60.dp).padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            //adds TextField where you can search for a pokemon
            TextField(
                value = pokemonName,
                onValueChange = { pokemonName = it },
                //adds text to textfiled when nothing is in it
                label = { Text("Enter Pokémon Name") },
                modifier = Modifier.fillMaxWidth()
            )
            //adds button so when you press it it sends a fetchPokemon with whatever name you typed in the textfield
            Button(onClick = {
                if (pokemonName.isNotBlank()) {
                    pokemonViewModel.fetchPokemon(pokemonName)
                }
            }) {
                //text for button
                Text("GO")
            }

            // Display Pokémon details
            pokemon?.let {
                Column(modifier = Modifier.padding(top = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                    //Image part gotten from chatGPT//
                    // Display the sprite image using Coil
                    Image(
                        //grabs the sprite image of wharever pokemon you searched
                        painter = rememberImagePainter(
                            it.sprites.front_default,
                            builder = {
                                // Optional: Apply transformations, e.g., CircleCrop
                                transformations(CircleCropTransformation())
                            }
                        ),
                        contentDescription = "Sprite of ${it.name}",
                        modifier = Modifier.size(100.dp),
                        contentScale = ContentScale.Fit
                    )

                    //gets name, hight, and weight and puts them on the screen
                    Text(text = "Name: ${it.name}")
                    Text(text = "Height: ${it.height} ft")
                    Text(text = "Weight: ${it.weight} lbs")


                }
            } ?: Text(text = "No Pokémon found.")//says this if no data is being displayed
        }
    }
}




