package com.astro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.astro.data.AstroResponse
import com.astro.model.AstroViewModel
import com.astro.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {

    private val viewModel: AstroViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.LightGray
                ) {
                    val navController = rememberNavController()
                    AppScaffold(navController,viewModel)
                }
            }
        }
    }
}


@Composable
fun HomeScreen(viewModel: AstroViewModel,navController: NavHostController) {
    InitUi(viewModel,navController)
}

@Composable
fun AstroDataDcreen(astroData: AstroResponse) {
    Text(
        text = astroData.data.toString(),
        modifier = Modifier.padding(top = 20.dp),
        style = MaterialTheme.typography.bodyMedium
    )
}

@Composable
fun AboutUsScreen() {
    InfoScreen(
        title = "About NumeroPitah",
        content = """
            NumeroPitah is a cutting-edge app that offers highly accurate predictions based on numerology, guiding users with personalized insights into their lives. Whether you're seeking clarity in your career, relationships, or personal growth, NumeroPitah harnesses the power of numbers to deliver precise, actionable advice.
            With its advanced algorithms and deep-rooted knowledge of numerology, NumeroPitah stands out as one of the best numerology apps in the world. It's designed for users seeking meaningful, data-driven predictions that lead to positive transformations and better decision-making.
        """
    )
}

@Composable
fun InfoScreen(title: String, content: String) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFFB2EBF2), Color(0xFF00796B))
                )
            )
            .padding(16.dp)
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Text(
                    text = title,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(12.dp))
            }
            item {
                Text(
                    text = content.trimIndent(),
                    fontSize = 18.sp,
                    color = Color.White
                )
            }
        }
    }
}


@Composable
fun ContactUsScreen() {
    InfoScreen(
        title = "Contact Us",
        content = """
            Address: 123 Astrology Lane, Gurugram, India
            Email: info@astropitah.com
            Phone: +1234567890
        """
    )
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
                MenuItem("Home", navController, "home", onMenuToggle)
                MenuItem("About Us", navController, "about", onMenuToggle)
                MenuItem("Contact Us", navController, "contact", onMenuToggle)
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Cyan)
    )
}




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppScaffold(navController: NavHostController, viewModel: AstroViewModel) {
    var showMenu by remember { mutableStateOf(false) }

    Scaffold(
        topBar = { AppTopBar(navController, showMenu, onMenuToggle = { showMenu = !showMenu }) }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("home") { HomeScreen(viewModel, navController) }
            composable("about") { AboutUsScreen() }
            composable("contact") { ContactUsScreen() }
             composable("astro_data_screen") {
                val astroData = viewModel.astroData.collectAsState().value
                astroData?.let {
                    AstroDataDcreen(it)
                }

            }
        }
    }


}

