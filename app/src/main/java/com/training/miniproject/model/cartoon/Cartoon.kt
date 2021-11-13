package com.training.miniproject.model.cartoon

import com.google.gson.annotations.SerializedName

data class Cartoon(
    @field:SerializedName("created")
    val created: String,
    @field:SerializedName("episode")
    val episode: List<String>,
    @field:SerializedName("gender")
    val gender: String,
    @field:SerializedName("id")
    val id: Int,
    @field:SerializedName("image")
    val image: String,
    @field:SerializedName("location")
    val location: Location,
    @field:SerializedName("name")
    val name: String,
    @field:SerializedName("origin")
    val origin: Origin,
    @field:SerializedName("species")
    val species: String,
    @field:SerializedName("status")
    val status: String,
    @field:SerializedName("type")
    val type: String,
    @field:SerializedName("url")
    val url: String
)