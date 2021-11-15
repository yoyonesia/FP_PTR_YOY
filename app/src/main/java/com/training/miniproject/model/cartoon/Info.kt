package com.training.miniproject.model.cartoon

import com.google.gson.annotations.SerializedName

data class Info(
    @field:SerializedName("count")
    val count: Int,
    @field:SerializedName("next")
    val next: String,
    @field:SerializedName("pages")
    val pages: Int,
    @field:SerializedName("prev")
    val prev: Any
)