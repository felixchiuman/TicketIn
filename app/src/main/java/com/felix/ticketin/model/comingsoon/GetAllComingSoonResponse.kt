package com.felix.ticketin.model.comingsoon


import com.google.gson.annotations.SerializedName

data class GetAllComingSoonResponse(
    @SerializedName("dates")
    val dates: Dates,
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val resultComingSoons: List<ResultComingSoon>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)