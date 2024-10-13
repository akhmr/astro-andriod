package com.astro.composable.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.astro.InfoScreen
import com.astro.InitUi
import com.astro.data.AstroResponse
import com.astro.model.AstroViewModel

@Composable
fun HomeScreen(viewModel: AstroViewModel, navController: NavHostController) {
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