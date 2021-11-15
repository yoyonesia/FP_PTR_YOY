package com.training.miniproject.model.cartoon

import com.google.gson.annotations.SerializedName

data class CartoonResponse(
    @field:SerializedName("info")
    val info: Info,
    @field:SerializedName("results")
    val cartoons: List<Cartoon>
)