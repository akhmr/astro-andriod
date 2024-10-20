package com.astro.model

import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController

class NavigationViewModel : ViewModel() {
    var navController: NavHostController? = null
}