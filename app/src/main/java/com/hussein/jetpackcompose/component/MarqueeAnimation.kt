
package com.hussein.jetpackcompose.component

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
@ExperimentalFoundationApi
fun MarqueeAnimation(){
    val focusRequester = remember {
        FocusRequester()
    }
    Column(modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment =Alignment.CenterHorizontally) {
        Text(
            text ="Welcome to Jetpack Compose",
            color=Color.White,
            modifier = Modifier
                .background(Color.Red)
                .padding(vertical = 10.dp)
                .basicMarquee(
                    iterations = Int.MAX_VALUE,
                    initialDelayMillis = 0,
                    //animationMode = MarqueeAnimationMode.WhileFocused,
                    delayMillis = 0,
                    velocity = 100.dp
                )
                //.focusRequester(focusRequester)
                //.focusable()
        )
    }
}