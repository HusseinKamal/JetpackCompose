package com.hussein.jetpackcompose.bottombar
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController


@Composable
fun BottomNavMainScreen(){
   val navController= rememberNavController()
    Scaffold(
        bottomBar = {
          BottomBar(navController = navController)
        },
    ){
        BottomNavGraph(navController = navController)
    }


}


@Composable
fun BottomBar(navController: NavController){
    val screens= listOf(BottomBarScreen.Home, BottomBarScreen.Profile, BottomBarScreen.Settings)

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    BottomNavigation {
        screens.forEach{screen->
            AddItem(screen = screen, currentDestination = currentDestination, navController = navController)
        }
    }
}
@Composable
fun RowScope.AddItem(screen: BottomBarScreen, currentDestination:NavDestination?, navController: NavController){
    BottomNavigationItem(
        label = { Text(text = screen.title)},
        icon= {Icon(imageVector = screen.icon , contentDescription = "Navigation Icon")},
        selected = currentDestination?.hierarchy?.any{
            it.route == screen.route
        }!!,
        unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
        onClick = {
            navController.navigate(screen.route){
            popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop=true
        }
        }
    )

}