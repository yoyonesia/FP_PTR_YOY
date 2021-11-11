package com.training.task2.model

import com.google.gson.annotations.SerializedName

data class BaseResponse(
    @field:SerializedName("info")
    val info: Info,
    @field:SerializedName("results")
    val cartoons: List<Cartoon>
)