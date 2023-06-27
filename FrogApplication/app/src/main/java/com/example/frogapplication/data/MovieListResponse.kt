package com.example.frogapplication.data

data class MovieListResponse(
    val Response: String,
    val Search: List<Search>,
    val totalResults: String
)