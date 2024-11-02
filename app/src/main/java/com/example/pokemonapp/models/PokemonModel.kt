package com.example.pokemonapp.models

data class Pokemon(
    val name : String,
    val sprites : Sprites,
    val weight : Number,
    val height : Number,
//    val Type : Array <String>
)

//got Sprites data class from noah
data class Sprites(
    val front_default : String
)
