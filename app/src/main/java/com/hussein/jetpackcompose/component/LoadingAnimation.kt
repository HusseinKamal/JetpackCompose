package com.hussein.jetpackcompose.component

import android.animation.Keyframe
import androidx.compose.animation.Animatable
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun LoadingAnimation(
    modifier: Modifier = Modifier,
    circleSize:Dp=25.dp,
    circleColor:Color=MaterialTheme.colors.primary,
    spaceBetween:Dp=10.dp,
    travelDistance:Dp=20.dp,
    ) {
    val circles= listOf(
        remember{ androidx.compose.animation.core.Animatable(initialValue = 0f) },
        remember{ androidx.compose.animation.core.Animatable(initialValue = 0f) },
        remember{ androidx.compose.animation.core.Animatable(initialValue = 0f) }
    )

    circles.forEachIndexed{index,animatable ->
        LaunchedEffect(key1 = animatable){
            delay(index * 100L)
            animatable.animateTo(
                targetValue = 1f ,
                animationSpec = infiniteRepeatable(
                    animation = keyframes{
                        durationMillis = 1200
                        0.0f at 0 with LinearOutSlowInEasing
                        1.0f at 300 with LinearOutSlowInEasing
                        0.0f at 600 with LinearOutSlowInEasing // return back to original position
                        0.0f at 1200 with LinearOutSlowInEasing
                    },
                    repeatMode = RepeatMode.Restart
                )
            )
        }
    }
    val circleValues= circles.map { it.value }

    val distances= with(LocalDensity.current){
        travelDistance.toPx()
    }

    val lastCircle= circleValues.size-1

    Row(modifier = modifier) {
        circleValues.forEachIndexed{ index,value ->
            Box(modifier = Modifier
                .size(circleSize)
                .graphicsLayer {
                    translationY = -value * distances
                }
                .background(color = circleColor, shape = CircleShape)
            )
            if(index != lastCircle){
                Spacer(modifier = Modifier.width(spaceBetween))
            }

        }
        
    }
}