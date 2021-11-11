package com.training.task2.model

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