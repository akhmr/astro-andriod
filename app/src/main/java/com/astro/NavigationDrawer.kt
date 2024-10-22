package com.astro



import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
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


data class NavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val navController: NavHostController,
    val route: String,

)


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationDrawerInitUI(navController: NavHostController,viewModel: AstroViewModel){

    val items = listOf(
        NavigationItem(
            title = "Home",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home,
            navController=navController,
            route=NavigationRoute.Home.route
        ),
        NavigationItem(
            title = "About us",
            selectedIcon = Icons.Filled.Info,
            unselectedIcon = Icons.Outlined.Info,
            navController=navController,
            route=NavigationRoute.About.route
        ),
        NavigationItem(
            title = "Contact us",
            selectedIcon = Icons.Filled.Settings,
            unselectedIcon = Icons.Outlined.Settings,
            navController=navController,
            route=NavigationRoute.Contact.route
        ),
    )

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    var selectedItemIndex by rememberSaveable {
        mutableIntStateOf(0)
    }
    ModalNavigationDrawer(

        drawerContent = {
            ModalDrawerSheet {
                Spacer(modifier = Modifier.height(16.dp).width(300.dp)
                    .padding(NavigationDrawerItemDefaults.ItemPadding))
                items.forEachIndexed { index, item ->
                    NavigationDrawerItem(
                        modifier = Modifier.width(250.dp),
                        label = {
                            Text(text = item.title)
                        },
                        selected = index == selectedItemIndex,
                        onClick = {

                            selectedItemIndex = index
                            scope.launch {
                                drawerState.close()
                            }
                            navController.navigate(item.route)
                        },
                        icon = {
                            Icon(
                                imageVector = if (index == selectedItemIndex) {
                                    item.selectedIcon
                                } else item.unselectedIcon,
                                contentDescription = item.title
                            )
                        },
                    )
                }
            }
        },
        drawerState = drawerState

    ){
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
            ) {

                 innerPadding ->
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


