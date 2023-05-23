package com.hussein.jetpackcompose.ui.theme

import androidx.compose.material.Colors
import androidx.compose.ui.graphics.Color
import com.hussein.jetpackcompose.color

val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)

val color1 = Color(0xFF642b73)
val color2 = Color(0xFFc6426e)

val Blue = Color(0xFF167AF6)
val Red = Color(0xFFe93560)
val Dark = Color(0xFF0E0D1D)
val Light = Color(0xFF17162B)

val Colors.topAppBarContentColor: Color
    get() = if(isLight) Color.White else Color.LightGray

val Colors.topAppBarBackgroundColor: Color
    get() = if(isLight) Purple500 else Color.Black

val Colors.hexaColor: Color
    get() = if(isLight) "#FF0055".color else Color.Black