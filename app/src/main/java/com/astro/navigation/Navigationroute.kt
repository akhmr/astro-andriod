package com.astro.navigation

import kotlinx.serialization.Serializable

sealed class NavigationRoute(val route: String) {
    object Home : NavigationRoute("home")
    object About : NavigationRoute("about")
    object Contact : NavigationRoute("about")
    object AstroScreen : NavigationRoute("astro_data_screen")

   /* @Serializable
    object Home : NavigationRoute("home")

    @Serializable
    object About : NavigationRoute("about")

    @Serializable
    object Contact : NavigationRoute("about")

    @Serializable
    object AstroScreen : NavigationRoute("astro_data_screen")*/


   /* data class AstroData(val id: String) : NavigationRoute("astro_data_screen/{id}") {
        fun createRoute(id: String) = "astro_data_screen/$id"
    }*/
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
