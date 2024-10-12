package com.astro.data

data class AstroResponse(
    val code: String,
    val message: String,
    val data: Data?
)