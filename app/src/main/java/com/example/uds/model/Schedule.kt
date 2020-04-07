package com.example.uds.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Schedule(
    @SerializedName("id")
    var id: String? = null,
    @SerializedName("title")
    var title: String? = null,
    @SerializedName("intro")
    var intro: String? = null,
    @SerializedName("description")
    var description: String? = null,
    @SerializedName("isDone")
    var isDone: Boolean? = false
) : Serializable