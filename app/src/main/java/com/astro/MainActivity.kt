package com.astro

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.astro.composable.component.AboutUsScreen
import com.astro.composable.component.AstroDataDcreen
import com.astro.composable.component.ContactUsScreen
import com.astro.composable.component.DisplayAstroDetail
import com.astro.composable.component.HomeScreen
import com.astro.data.AstroDto
import com.astro.data.AstroNumSubcategory
import com.astro.model.AstroViewModel
import com.astro.model.NavigationViewModel
import com.astro.navigation.GlobalNavigation
import com.astro.navigation.NavigationRoute
import com.astro.ui.theme.AstroAppTheme
import kotlinx.coroutines.launch
import com.astro.navigation.NavigationRoute.About.route as route1

class MainActivity : ComponentActivity() {

    private val viewModel: AstroViewModel by viewModels()
    private val navigationViewModel: NavigationViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AstroAppTheme {
                val navController = rememberNavController()
                GlobalNavigation.navController = navController
               NavigationDrawerInitUI(navController,viewModel)
              // AppScaffold(navController, viewModel)
            }
        }

    }
}




