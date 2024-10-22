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



