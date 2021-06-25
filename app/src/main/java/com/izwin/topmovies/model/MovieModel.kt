package com.izwin.topmovies.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class MovieModel(
    val multimedia: MultimediaModel,
    val link: LinkModel,
    @SerializedName("publication_date")
    val date: String,
    @SerializedName("mpaa_rating")
    val rating: String,
    val headline: String,
    @SerializedName("byline")
    val author: String,
    @SerializedName("display_title")
    val title: String) : Serializable
