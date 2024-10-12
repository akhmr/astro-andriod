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
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppScaffold(navController: NavHostController, viewModel: AstroViewModel) {
    var showMenu by remember { mutableStateOf(false) } // State to toggle menu
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("NumeroPitah") },
                navigationIcon = {
                    IconButton(onClick = { showMenu = !showMenu }) { // Toggle the menu visibility
                        Icon(imageVector = Icons.Default.Menu, contentDescription = "Menu")
                    }
                    // DropdownMenu placed below the three lines (menu icon)
                    DropdownMenu(
                        expanded = showMenu,
                        onDismissRequest = { showMenu = false },
                        modifier = Modifier
                            .width(140.dp) // Adjust the width if needed
                            .background(Color.DarkGray) // Dark gray background color
                    ) {
                        DropdownMenuItem(
                            text = {
                                Text(
                                    "Home",
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 16.sp, // Sober font style
                                    color = Color.White
                                )
                            },
                            onClick = {
                                navController.navigate("home")
                                showMenu = false // Close the menu when an item is clicked
                            },
                            modifier = Modifier.padding(vertical = 4.dp) // Reduce vertical padding
                        )
                        DropdownMenuItem(
                            text = {
                                Text(
                                    "About Us",
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 16.sp,
                                    color = Color.White
                                )
                            },
                            onClick = {
                                navController.navigate("about")
                                showMenu = false // Close the menu
                            },
                            modifier = Modifier.padding(vertical = 4.dp)
                        )
                        DropdownMenuItem(
                            text = {
                                Text(
                                    "Contact Us",
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 16.sp,
                                    color = Color.White
                                )
                            },
                            onClick = {
                                navController.navigate("contact")
                                showMenu = false // Close the menu
                            },
                            modifier = Modifier.padding(vertical = 2.dp)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Cyan)
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("home") { HomeScreen(viewModel) }
            composable("about") { AboutUsScreen() }
            composable("contact") { ContactUsScreen() }
        }
    }
}

@Composable
fun HomeScreen( viewModel: AstroViewModel) {
    InitUi(viewModel) // Your Home screen is the form you have created
}

@Composable
fun AboutUsScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFB2EBF2), // Light cyan-like color
                        Color(0xFF00796B)  // Dark teal-like color
                    )
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
                    text = "About NumeroPitah",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White // Make the text white for contrast
                )
                Spacer(modifier = Modifier.height(12.dp)) // Add space between the title and description
            }
            item {
                Text(
                    text = "NumeroPitah is a cutting-edge app that offers highly accurate predictions based on numerology, guiding users with personalized insights into their lives. Whether you're seeking clarity in your career, relationships, or personal growth, NumeroPitah harnesses the power of numbers to deliver precise, actionable advice.NumeroPitah is a cutting-edge app that offers highly accurate predictions based on numerology, guiding users with personalized insights into their lives. Whether you're seeking clarity in your career, relationships, or personal growth, NumeroPitah is a cutting-edge app that offers highly accurate predictions based on numerology, guiding users with personalized insights into their lives. Whether you're seeking clarity in your career, relationships, or personal growth, NumeroPitah harnesses the power of numbers to deliver precise, actionable advice.NumeroPitah is a cutting-edge app that offers highly accurate predictions based on numerology, guiding users with personalized insights into their lives. Whether you're seeking clarity in your career, relationships, or personal growth, NumeroPitah harnesses the power of numbers to deliver precise, actionable advice.NumeroPitah harnesses the power of numbers to deliver precise, actionable advice.",
                    fontSize = 18.sp,
                    color = Color.White // White text for contrast
                )
                Spacer(modifier = Modifier.height(12.dp)) // Add space below the first paragraph
            }
            item {
                Text(
                    text = "With its advanced algorithms and deep-rooted knowledge of numerology, NumeroPitah stands out as one of the best numerology apps in the world. It's designed for users seeking meaningful, data-driven predictions that lead to positive transformations and better decision-making.",
                    fontSize = 18.sp,
                    color = Color.White // White text for contrast
                )
            }
        }
    }
}

@Composable
fun ContactUsScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFB3E5FC), // Light blue color
                        Color(0xFF0288D1)  // Darker blue color
                    )
                )
            )
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Contact Us",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White // White text for contrast
            )
            Spacer(modifier = Modifier.height(12.dp)) // Add space between the title and contact details
            Text(
                text = "Address: 123 Astrology Lane, Gurugram, India",
                fontSize = 18.sp,
                color = Color.White // White text
            )
            Spacer(modifier = Modifier.height(4.dp)) // Add smaller spacing between details
            Text(
                text = "Email: info@astropitah.com",
                fontSize = 18.sp,
                color = Color.White // White text
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Phone: +1234567890",
                fontSize = 18.sp,
                color = Color.White // White text
            )
        }
    }
}

