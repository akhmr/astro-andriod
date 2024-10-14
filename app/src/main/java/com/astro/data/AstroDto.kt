package com.astro.data

data class AstroDto(
    val astroNumSubcategories: List<AstroNumSubcategory>,
    val displayName: String,
    val num: Int,
    val numType: String
)