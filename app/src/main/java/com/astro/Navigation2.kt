package com.astro

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.astro.composable.component.AboutUsScreen
import com.astro.composable.component.AstroDataDcreen
import com.astro.composable.component.ContactUsScreen
import com.astro.composable.component.DisplayAstroDetail
import com.astro.composable.component.HomeScreen
import com.astro.data.AstroDto
import com.astro.model.AstroViewModel
import com.astro.navigation.NavigationRoute
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppScaffold(navController: NavHostController, viewModel: AstroViewModel) {
    var showMenu by remember { mutableStateOf(false) }
    var drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {

            DrawerContent(
                onDestinationClicked = { route ->
                    scope.launch {
                        drawerState.close() // Close the drawer
                    }
                    navController.navigate(route)
                }
            )
        }
    ) {
        Scaffold(
            topBar = {
                AppTopBar(
                    navController,
                    onMenuToggle = {
                        scope.launch {
                            if (drawerState.isClosed) drawerState.open() else drawerState.close()
                        }
                    }
                )
            }
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
                composable(route = NavigationRoute.AstroDetail.route) {
                    val astroDto = navController.currentBackStackEntry?.savedStateHandle?.get<AstroDto>("astroDto")
                    if (astroDto != null) {
                        DisplayAstroDetail(astroDto)
                    }
                }
            }
        }
    }
}

@Composable
fun MenuItem(text: String, onClick: () -> Unit) {
    Text(
        text = text,
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal,
        color = Color.White,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onClick() }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    navController: NavHostController,
    onMenuToggle: () -> Unit
) {
    var currentRoute by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(navController) {
        navController.currentBackStackEntryFlow.collect { backStackEntry ->
            currentRoute = backStackEntry.destination.route
        }
    }

    TopAppBar(
        title = { Text("NumeroPitah") },
        navigationIcon = {
            if (currentRoute != NavigationRoute.Home.route) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                }
            } else {
                IconButton(onClick = onMenuToggle) {
                    Icon(imageVector = Icons.Default.Menu, contentDescription = "Menu")
                }
            }
        },
        /* colors = TopAppBarDefaults.topAppBarColors(
             containerColor = Color(0xFFB2EBF2)
         )*/
    )
}

@Composable
fun DrawerContent(onDestinationClicked: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            // .background(Color.DarkGray)
            .padding(16.dp)
    ) {
        Text(
            text = "Menu",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        MenuItem("Home", onClick = { onDestinationClicked(NavigationRoute.Home.route) })
        MenuItem("About Us", onClick = { onDestinationClicked(NavigationRoute.About.route) })
        MenuItem("Contact Us", onClick = { onDestinationClicked(NavigationRoute.Contact.route) })
    }
}