package com.hussein.jetpackcompose

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.hussein.jetpackcompose.ui.theme.Shapes

@ExperimentalMaterialApi
@Composable
fun GoogleButton(text:String="Sign up with Google",
                 loadingText:String="Creating Account ...",
                 icon:Painter= painterResource(id = R.drawable.ic_google_button),
                 shape:androidx.compose.ui.graphics.Shape= Shapes.medium,
                 borderColor:Color=Color.LightGray,
                 backgroundColor: Color=MaterialTheme.colors.surface,
                 progressIndicatorColor: Color=MaterialTheme.colors.primary,
                 onClicked:()-> Unit){
    var clicked by remember {
        mutableStateOf(false)
    }
    Surface(
        onClick = { clicked=!clicked},
        shape = shape,
        border = BorderStroke(width = 1.dp, color = borderColor),
        color = backgroundColor
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(start = 12.dp, end = 16.dp, top = 12.dp, bottom = 12.dp)
                .animateContentSize(
                    animationSpec = tween(
                        durationMillis = 300,
                        easing = LinearOutSlowInEasing
                    )
                )
        ) {
            Icon(modifier = Modifier
                .width(15.dp)
                .height(15.dp),painter = icon,
                contentDescription = "Google Button",
            tint = Color.Unspecified)
            Spacer(modifier = Modifier.width(8.dp))
            Text( if(clicked) loadingText else text)
            Spacer(modifier = Modifier.width(16.dp))
            if(clicked)
            {
                CircularProgressIndicator(modifier = Modifier
                    .height(16.dp)
                    .width(16.dp),
                strokeWidth = 2.dp,
                color = progressIndicatorColor)
                onClicked()
            }
        }
    }
}

@ExperimentalMaterialApi
@Composable
@Preview
fun GoogleButtonPreview(){
    GoogleButton(onClicked = {})
}