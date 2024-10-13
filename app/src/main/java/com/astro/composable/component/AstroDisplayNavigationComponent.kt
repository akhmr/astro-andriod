package com.astro.composable.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.astro.data.AstroResponse

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp




@Composable
fun Header(title: String) {
    Text(
        text = title,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.primary
    )
}

@Composable
fun CategoryGrid(textToDisplay: String) {
    val categories = listOf(
        "Compatibility", "Secondary Biorhythms", "Life Path Number",
        "Psychomatrix", "Psychomatrix Lines", "Soul Number",
        "Challenge Number", "Name Number"
    )
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.padding(8.dp),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(categories.size) { index ->
            CategoryCard(title = categories[index],textToDisplay)
        }
    }
}

@Composable
fun CategoryCard(title: String, textToDisplay: String) {
    Card(
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1.5f)
            .clickable { /* Handle click */ }
            .padding(8.dp)
    ) {
        Box(
            modifier = Modifier
                .background(Color(0xFF24316A))
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = textToDisplay,
                color = Color.White,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp
            )
        }
    }
}

@Composable
fun AstroDataDcreen(astroData: AstroResponse) {

    Column {
        Header(title = "October 13")
        CategoryGrid(astroData.data.toString())
    }
    Text(
        text = astroData.data.toString(),
        modifier = Modifier.padding(top = 20.dp),
        style = MaterialTheme.typography.bodyMedium
    )
}