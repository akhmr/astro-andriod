package com.astro.data

data class Data(
    val astroDtos: List<AstroDto>)
{
    fun getDisplayNames(): List<String> {
        return astroDtos.map { it.displayName }
    }
}