package com.hussein.jetpackcompose.component

import androidx.compose.animation.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

@Composable
fun SelectableItem(
    modifier: Modifier = Modifier,
    selected : Boolean,
    title: String,
    titleColor : Color =
        if(selected) MaterialTheme.colors.primary else MaterialTheme.colors.primary.copy(alpha = 0.2f),
    titleSize : TextUnit = MaterialTheme.typography.h6.fontSize,
    titleWeight : FontWeight =FontWeight.Medium,
    subtitle : String ? =null,
    subtitleColor : Color =
        if(selected) MaterialTheme.colors.onSurface else MaterialTheme.colors.primary.copy(alpha = 0.2f),
    borderWidth : Dp = 1.dp,
    borderColor : Color =
        if(selected) MaterialTheme.colors.primary else MaterialTheme.colors.primary.copy(alpha = 0.2f),
    borderShape : Shape= RoundedCornerShape(size = 10.dp),
    icon : ImageVector = Icons.Default.CheckCircle,
    iconColor : Color =
        if(selected) MaterialTheme.colors.primary else MaterialTheme.colors.primary.copy(alpha = 0.2f),
    onClick: ()-> Unit
    ) {
    val scaleA= remember {
        androidx.compose.animation.core.Animatable(initialValue = 1f)
    }
    val scaleB= remember {
        androidx.compose.animation.core.Animatable(initialValue = 1f)
    }
    LaunchedEffect(key1 = selected){
        if(selected) {
            launch {
                scaleA.animateTo(targetValue = 0.3f, animationSpec = tween(durationMillis = 50))
            }
            launch {
                scaleA.animateTo(targetValue = 1f, animationSpec = spring(
                    dampingRatio = Spring.DampingRatioLowBouncy,
                    stiffness = Spring.StiffnessLow
                ))
            }
            launch {
                scaleB.animateTo(targetValue = 0.9f, animationSpec = tween(durationMillis = 50))
            }
            launch {
                scaleB.animateTo(targetValue = 1f, animationSpec = spring(
                    dampingRatio = Spring.DampingRatioLowBouncy,
                    stiffness = Spring.StiffnessLow
                ))
            }
        }
    }
    Column(modifier = modifier
        .scale(scale = scaleB.value)
        .border(
            width = borderWidth,
            color = borderColor,
            shape = borderShape
        )
        .clip(borderShape)
        .clickable { onClick() }
    ) {
        Row(modifier = Modifier.padding(start = 12.dp), verticalAlignment = Alignment.CenterVertically){
            Text(text = title,
                modifier = Modifier.weight(8f),
                style = TextStyle(color = titleColor , fontSize = titleSize, fontWeight = titleWeight),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            IconButton(
                onClick = { onClick() },
                modifier =Modifier.weight(2f).scale(scale = scaleA.value)
            ) {
                Icon(imageVector = icon, contentDescription = "Selectable Item Icon" , tint = iconColor)
                
            }
        }
        if(subtitle!=null){
            Text(
                text = subtitle,
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .padding(bottom = 12.dp),
                style = TextStyle(color = subtitleColor),
                maxLines = 3 ,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}