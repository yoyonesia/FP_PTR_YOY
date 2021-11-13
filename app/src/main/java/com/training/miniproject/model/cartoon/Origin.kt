package com.training.miniproject.model.cartoon

import com.google.gson.annotations.SerializedName

data class Origin(
    @field:SerializedName("name")
    val name: String,
    @field:SerializedName("url")
    val url: String
)