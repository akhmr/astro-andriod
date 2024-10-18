package com.astro

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.astro.data.AstroDto
import com.astro.ui.theme.MyApplicationTheme

class NumCategoryDetail : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val astroDto: AstroDto? = intent.getParcelableExtra("astroDto")
        setContent {
            MyApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    astroDto?.let {
                        DisplayAstroDto(
                            astroDto = it,
                            modifier = Modifier.padding(innerPadding)
                        )
                    }
                }
            }
        }
    }
}

    @Composable
    fun DisplayAstroDto(astroDto: AstroDto, modifier: Modifier = Modifier) {
        Log.d("Hello categories ", astroDto.displayName)
        Column(modifier = modifier.padding(16.dp)) {
            Text(
                text = "Name: ${astroDto.displayName}",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Description: ${astroDto.displayName}",
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal
            )
        }
    }

