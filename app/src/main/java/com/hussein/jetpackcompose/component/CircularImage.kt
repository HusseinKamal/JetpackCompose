package com.hussein.jetpackcompose.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hussein.jetpackcompose.R

@Composable
fun CircularImage() {
    Image(modifier = Modifier.size(300.dp)
        .clip(CircleShape)
        .border(width = 10.dp, color = Color.Cyan , shape = CircleShape)
        //.clip(RoundedCornerShape(size= 10.dp))
        //.border(width = 10.dp, color = Color.Cyan , shape = RoundedCornerShape(50.dp))
        ,painter = painterResource(id = R.drawable.ic_launcher_background),
        contentDescription = "Circular Image")
}

@Composable
@Preview(showBackground = true)
fun CircularImagePreview() {
    CircularImage()
}