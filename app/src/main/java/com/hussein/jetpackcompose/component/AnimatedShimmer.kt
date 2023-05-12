package com.hussein.jetpackcompose.component

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Colors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun AnimatedShimmer(){
    val shimmerColor= listOf(
        Color.LightGray.copy(alpha= 0.6f) ,
        Color.LightGray.copy(alpha= 0.2f) ,
        Color.LightGray.copy(alpha= 0.6f))

    val transition = rememberInfiniteTransition()

    val translateAnim = transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000, easing = FastOutSlowInEasing)
        )
    )

    val brush = Brush.linearGradient(
        colors = shimmerColor,
        start = Offset.Zero ,
        end = Offset(x=translateAnim.value,y=translateAnim.value)
    )
    ShimmerGridItem(brush = brush)
}

@Composable
fun ShimmerGridItem(brush: Brush) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(all = 10.dp),
        verticalAlignment = Alignment.CenterVertically) {
        Spacer(modifier = Modifier
            .size(80.dp)
            .clip(RoundedCornerShape(size = 10.dp))
            .background(brush))

        Spacer(modifier = Modifier.padding(5.dp))
        
        Column(verticalArrangement = Arrangement.Center) {
            Spacer(modifier = Modifier.height(20.dp).fillMaxWidth(fraction = 0.7f).background(brush))
            Spacer(modifier = Modifier.height(10.dp))
            Spacer(modifier = Modifier.height(20.dp).fillMaxWidth(fraction = 0.9f).background(brush))
            
        }
        
    }
}

@Composable
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
fun AnimatedShimmerPreview(){
    AnimatedShimmer()
}