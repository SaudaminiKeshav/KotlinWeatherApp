package com.androidapp.kotlinweatherapp.model

import com.google.gson.annotations.SerializedName

class City(
    @SerializedName("id") val id : Int,
    @SerializedName("name") val name : String,
    @SerializedName("coord") val coord : Coord,
    @SerializedName("country") val country : String
)
