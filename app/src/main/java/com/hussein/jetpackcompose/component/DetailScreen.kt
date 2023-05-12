package com.hussein.jetpackcompose.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.hussein.jetpackcompose.Screen

@Composable
fun DetailScreen(navController: NavController) {
    Box(modifier =Modifier.fillMaxSize(), contentAlignment = Alignment.Center ){
        Text(
            modifier = Modifier.clickable {
                navController.navigate(route = Screen.Home.route){
                    popUpTo(Screen.Home.route){
                        inclusive =true
                    }
                }
                //navController.popBackStack()
            },
            text = "Detail",
            color = Color.Red,
            fontSize = MaterialTheme.typography.h3.fontSize,
            fontWeight = FontWeight.Bold
        )

    }
}

@Composable
@Preview(showBackground = true)
fun DetailScreenPreview() {
    DetailScreen(navController = rememberNavController())
}

