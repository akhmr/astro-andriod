package com.astro.data
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class AstroDto(
    val astroNumSubcategories: List<AstroNumSubcategory>,
    val displayName: String,
    val num: Int,
    val numType: String
): Parcelable