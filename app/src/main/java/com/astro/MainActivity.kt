package com.astro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.astro.composable.component.AboutUsScreen
import com.astro.composable.component.AstroDataDcreen
import com.astro.composable.component.ContactUsScreen
import com.astro.composable.component.HomeScreen
import com.astro.model.AstroViewModel
import com.astro.navigation.NavigationRoute
import com.astro.ui.theme.AstroAppTheme
import com.astro.ui.theme.MyApplicationTheme
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

class MainActivity : ComponentActivity() {

    private val viewModel: AstroViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AstroAppTheme {
                val navController = rememberNavController()
                AppScaffold(navController, viewModel)
            }
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppScaffold(navController: NavHostController, viewModel: AstroViewModel) {
    var showMenu by remember { mutableStateOf(false) }
    val navController = rememberNavController()

    Scaffold(
        topBar = { AppTopBar(navController, showMenu, onMenuToggle = { showMenu = !showMenu }) }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = NavigationRoute.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(NavigationRoute.Home.route) { HomeScreen(viewModel, navController) }
            composable(NavigationRoute.About.route) { AboutUsScreen() }
            composable(NavigationRoute.Contact.route) { ContactUsScreen() }
            composable(NavigationRoute.AstroScreen.route) {
                val astroData = viewModel.astroData.collectAsState().value
                astroData?.let {
                    AstroDataDcreen(viewModel, it)
                }

            }
        }
    }
}



@Composable
fun MenuItem(
    text: String,
    navController: NavHostController,
    route: String,
    onClick: () -> Unit
) {
    DropdownMenuItem(
        text = {
            Text(text, fontWeight = FontWeight.Normal, fontSize = 16.sp, color = Color.White)
        },
        onClick = {
            navController.navigate(route)
            onClick()
        },
        modifier = Modifier.padding(vertical = 4.dp)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    navController: NavHostController,
    showMenu: Boolean,
    onMenuToggle: () -> Unit
) {
    TopAppBar(
        title = { Text("NumeroPitah") },
        navigationIcon = {
            IconButton(onClick = onMenuToggle) {
                Icon(imageVector = Icons.Default.Menu, contentDescription = "Menu")
            }
            DropdownMenu(
                expanded = showMenu,
                onDismissRequest = { onMenuToggle() },
                modifier = Modifier
                    .width(140.dp)
                    .background(Color.DarkGray)
            ) {

                MenuItem("Home", navController, NavigationRoute.Home.route, onMenuToggle)
                MenuItem("About Us", navController, NavigationRoute.About.route, onMenuToggle)
                MenuItem("Contact Us", navController, NavigationRoute.Contact.route, onMenuToggle)
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Cyan)
    )
}


