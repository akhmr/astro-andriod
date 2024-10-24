package com.astro.data
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class AstroNumSubcategory(
    val displayName: String,
    val negTrait: String? = null,
    val posTrait: String,
    val remedy: String?=null
) : Parcelable
