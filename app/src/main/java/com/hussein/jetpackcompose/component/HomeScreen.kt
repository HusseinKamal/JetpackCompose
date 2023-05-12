package com.hussein.jetpackcompose.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.hussein.jetpackcompose.Screen

@Composable
fun HomeScreen(navController: NavController) {
    Box(modifier =Modifier.fillMaxSize(), contentAlignment = Alignment.Center ){
        Text(
            modifier = Modifier.clickable {
                //navController.navigate(route = Screen.Detail.route)
                //navController.navigate(route = Screen.Detail.passID(1))
                //navController.navigate(route = Screen.Detail.passIDName(1,"Hussein"))
                navController.navigate(route = Screen.Detail.passID(1))
            },
            text = "Home",
            color = MaterialTheme.colors.primary,
            fontSize = MaterialTheme.typography.h3.fontSize,
            fontWeight = FontWeight.Bold
        )

    }
}

@Composable
@Preview(showBackground = true)
fun HomeScreenPreview() {
    HomeScreen(navController = rememberNavController())
}

