package com.astro.navigation

import com.astro.data.AstroDto
import kotlinx.serialization.Serializable

sealed class NavigationRoute(val route: String) {
    object Home : NavigationRoute("home")
    object About : NavigationRoute("about")
    object Contact : NavigationRoute("about")
    object AstroScreen : NavigationRoute("astro_data_screen")
   // object AstroDetail : NavigationRoute("astro_detail")

    object AstroDetail : NavigationRoute("astro_detail")
    }







/*
@Serializable
object home

@Serializable
object aboutus


@Serializable
object contactus

@Serializable
object AstroDataScreen*/
