package com.hussein.jetpackcompose.component

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import com.github.skydoves.colorpicker.compose.*
import com.hussein.jetpackcompose.R

@Composable
fun ColorPicker(){
    /*val controller = rememberColorPickerController()
    ImageColorPicker(
        modifier = Modifier
            .fillMaxWidth()
            .height(450.dp)
            .padding(10.dp),
        controller = controller,
        paletteImageBitmap = ImageBitmap.imageResource(R.drawable.ic_launcher_background),
        paletteContentScale = PaletteContentScale.FIT,
        onColorChanged = { colorEnvelope: ColorEnvelope ->
            // do something
        }
    )*/
    val controller = rememberColorPickerController()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 30.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            AlphaTile(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .clip(RoundedCornerShape(6.dp)),
                controller = controller
            )
        }
        HsvColorPicker(
            modifier = Modifier
                .fillMaxWidth()
                .height(450.dp)
                .padding(10.dp),
            controller = controller,
            onColorChanged = {
                Log.d("Color", it.hexCode)
            }
        )
        AlphaSlider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .height(35.dp),
            controller = controller,
            tileOddColor = Color.White,
            tileEvenColor = Color.Black
        )
        BrightnessSlider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .height(35.dp),
            controller = controller,
        )
    }
}


@Composable
fun ImageColorPicker() {
    val controller = rememberColorPickerController()

    Column(modifier = Modifier.fillMaxSize()) {
        ImageColorPicker(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp),
            paletteImageBitmap = ImageBitmap.imageResource(R.drawable.ic_launcher_background),
            controller = controller,
            paletteContentScale = PaletteContentScale.FIT,
            onColorChanged = { colorEnvelope: ColorEnvelope ->
                Log.d("MainScreen", "${colorEnvelope.color}")
            }
        )
        Spacer(modifier = Modifier.height(12.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            AlphaTile(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .clip(RoundedCornerShape(6.dp)),
                controller = controller
            )
        }
//        Box(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(horizontal = 30.dp)
//                .height(60.dp)
//                .background(color = controller.selectedColor.value)
//        ) {}
        AlphaSlider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .height(35.dp),
            controller = controller,
//            tileOddColor = Color.White,
//            tileEvenColor = Color.Black
        )
        BrightnessSlider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .height(35.dp),
            controller = controller,
        )
    }
}