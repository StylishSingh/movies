package com.bigstep.assignment


import com.google.gson.annotations.SerializedName

data class VideoModel(
    @SerializedName("resultCount")
    val resultCount: Int,
    @SerializedName("results")
    val videoResults: MutableList<VideoResult>
)