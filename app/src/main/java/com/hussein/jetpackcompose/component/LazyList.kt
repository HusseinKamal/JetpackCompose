package com.hussein.jetpackcompose.component

import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

@Composable
fun LazyList(){
    val lazyList= rememberLazyListState()
    Scaffold(content = {
        Box(modifier = Modifier.fillMaxSize()) {
            ListItem(lazyList)
            Text(
                text = if(lazyList.isScrolled) "Scrolling" else "Idle",
                modifier = Modifier.align(Alignment.Center),
                style = TextStyle(fontSize = MaterialTheme.typography.h6.fontSize)
            )
        }
    })

}

@Composable
fun ListItem(lazyList : LazyListState){
    val items = remember {
        List(size = 25){ it}
    }
     LazyColumn(contentPadding = PaddingValues(12.dp), state = lazyList, verticalArrangement = Arrangement.spacedBy(24.dp)){
         items(items=items, key = {it}){
             ItemHolder()
         }
     }
}

@Composable
fun ItemHolder(){
   Spacer(modifier = Modifier.fillMaxWidth().height(20.dp).background(Color.LightGray.copy(alpha = ContentAlpha.disabled)))
}

val LazyListState.isScrolled : Boolean
    get() = firstVisibleItemIndex > 0 || firstVisibleItemScrollOffset >0