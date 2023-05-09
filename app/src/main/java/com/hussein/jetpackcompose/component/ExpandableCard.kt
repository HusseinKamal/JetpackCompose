package com.hussein.jetpackcompose

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.hussein.jetpackcompose.ui.theme.Shapes

@ExperimentalMaterialApi
@Composable
fun ExpandableCard(tile:String,
        fontSize:TextUnit=MaterialTheme.typography.h6.fontSize,
        textFontWeight: FontWeight=FontWeight.Bold
){
    var expandableState by remember {
        mutableStateOf(false)
    }

    val rotateState by animateFloatAsState(targetValue = if (expandableState) 180f else 0f)

    Card(modifier = Modifier
        .fillMaxWidth()
        .animateContentSize(
            animationSpec =
            tween(
                durationMillis = 300,
                easing = LinearOutSlowInEasing
            )
        ),
        shape = Shapes.medium,
        onClick = {
            expandableState =!expandableState
        }
    ) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(modifier = Modifier.weight(6f),//Compose row with 7 sections 6 for title and 1 for drop down arrow
                    text = tile,
                    fontSize = fontSize,
                    fontWeight = textFontWeight,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                IconButton(modifier = Modifier
                    .alpha(ContentAlpha.medium)
                    .weight(1f)
                    .rotate(rotateState)
                    ,onClick = {
                        expandableState =!expandableState
                    }) {
                    Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = "Drop Down Arroa")
                }
            }
            if(expandableState)
                Text(text ="Text Description Test",
                fontSize = MaterialTheme.typography.subtitle1.fontSize,
                fontWeight = FontWeight.Normal,
                maxLines = 4,
                overflow = TextOverflow.Ellipsis)
        }
    }
}

@ExperimentalMaterialApi
@Composable
@Preview
fun ExpandableCardPreview(){
    ExpandableCard("My Title")
}