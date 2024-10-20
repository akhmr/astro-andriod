package com.astro.composable.component

import android.net.Uri
import android.util.Log
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.astro.data.AstroDto
import com.astro.data.AstroNumSubcategory
import com.astro.data.Data
import com.astro.model.AstroViewModel
import com.astro.model.NavigationViewModel
import com.astro.navigation.GlobalNavigation
import com.astro.navigation.NavigationRoute
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json


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
fun CategoryGrid(astroResponse: AstroResponse) {

    val data: Data? = astroResponse.data
    val categories: List<String> = data?.getDisplayNames() ?: emptyList() // U
    Log.d("Hello categories ", categories.toString())
   // var selectedAstroDto by remember { mutableStateOf<AstroDto?>(null) }
    val context = LocalContext.current //
    val navigationViewModel: NavigationViewModel = viewModel()
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.padding(8.dp),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(categories.size) { index ->
            CategoryCard(title = categories[index], categories[index]) {
                // Handle click event here, such as navigating to NumCategoryDetail
                val category = categories[index]
                val astroDto = data?.astroDtos?.get(index)

               // selectedAstroDto = data?.astroDtos?.get(index)
                Log.d("navigationViewModel ",GlobalNavigation.navController.toString())
              //  if (astroDto != null) {
                   // navController.navigate("astro_detail/${astroDto.id}") // Use the ID of the astroDto
                //    navController.navigate(NavigationRoute.AstroScreen.route).navController?.navigate(("astro_detail"))


                Log.d("Hello astroJson  ",astroDto.toString())
                val astroJson = Json.encodeToString(astroDto)
                Log.d("Hello json  ",astroJson.toString())
               //GlobalNavigation.navController?.currentBackStackEntry?.savedStateHandle?.set("astroDto", astroDto)
                GlobalNavigation.navController?.navigate(NavigationRoute.AstroDetail.route)

                GlobalNavigation.navController?.currentBackStackEntry?.savedStateHandle?.set("astroDto", astroDto)

               // }


                //astroDto?.let {
                   /* val intent = Intent(context, NumCategoryDetail::class.java).apply {
                        putExtra("astroDto", it)*/


                  //  }
                   // context.startActivity(intent)
                }
            }
        }


    }




@Composable
fun CategoryCard(title: String, textToDisplay: String, onClick:  () -> Unit ) {
    Card(
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1.5f)
            .clickable {  onClick()  }
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
fun DisplayAstroDetail(astroDto: AstroDto?) {
    if (astroDto != null) {
        Log.d("Name ", astroDto.displayName)
    }
    Column(modifier = Modifier.fillMaxSize().fillMaxWidth()) {
        if (astroDto != null) {
            Text(
                text = "Name: ${astroDto.displayName}",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }


        if (astroDto != null) {
            Text(
                text = "${astroDto.displayName}",
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.padding(vertical = 4.dp)
            )
        }

    }
}

/*@Composable
fun DisplayAstroDetail(astroDto: AstroDto) {
    Log.d("Hello categories ", astroDto.displayName)
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Name: ${astroDto.displayName}",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        astroDto.astroNumSubcategories.forEach{subcategory->
            Text(
                text = "${subcategory.posTrait}",
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.padding(vertical = 4.dp)
            )

            Text(
                text = "${subcategory.negTrait}",
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.padding(vertical = 4.dp)
            )

            Text(
                text = "${subcategory.remedy}",
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.padding(vertical = 4.dp)
            )


        }

    }
}*/



@Composable
fun AstroDataDcreen(viewModel: AstroViewModel, astroData: AstroResponse) {

    val astroResponse by viewModel.astroData.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    if (isLoading) {
        // Show loading indicator
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            CircularProgressIndicator() // Show loading spinner
        }
    } else {

        Column {
           // Header(title = "October 13")
            CategoryGrid(astroData)
        }

    }
}