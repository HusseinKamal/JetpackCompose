package com.hussein.jetpackcompose.search

import android.util.Log
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun SearchMainScreen(viewModel: MainViewModel){

    val searchWidgetStates by  viewModel.searchWidgetState

    val searchTextStates by  viewModel.searchTextState


    Scaffold(topBar = {
       MainAppBarSearch(
           searchWidgetStates = searchWidgetStates,
           searchTextState = searchTextStates,
           onTextChange ={ viewModel.updateSearchTextState(newValue = it)} ,
           onCloseClicked = {
               viewModel.updateSearchTextState(newValue = "")
               viewModel.updateSearchWidgetState(newValue = SearchWidgetStates.CLOSED)
                            },
           onSearchClicked = {
                             Log.d("Search Value", it)
           },
           onSearchTriggered = {
               viewModel.updateSearchWidgetState(newValue = SearchWidgetStates.OPEND)
           }
       )
    }){}
}

@Composable
fun MainAppBarSearch(
    searchWidgetStates: SearchWidgetStates,
    searchTextState: String,
    onTextChange: (String) -> Unit,
    onCloseClicked: () -> Unit,
    onSearchClicked: (String) -> Unit,
    onSearchTriggered: () -> Unit
){
    when(searchWidgetStates){
        SearchWidgetStates.CLOSED ->{
            DefaultAppBar (
                onSearchClick = onSearchTriggered
            )
        }
        SearchWidgetStates.OPEND ->{
            SearchAppBar(
                text = searchTextState,
                onTextChange = onTextChange,
                onCloseClicked = onCloseClicked,
                onSearchClicked =  onSearchClicked
            )
        }
    }
}

@Composable
fun SearchAppBar(text:String, onTextChange: (String) -> Unit,onCloseClicked: () -> Unit,onSearchClicked: (String) -> Unit){
    Surface(modifier = Modifier
        .fillMaxWidth()
        .height(56.dp), elevation = AppBarDefaults.TopAppBarElevation, color = MaterialTheme.colors.primary) {
       TextField(
           modifier = Modifier.fillMaxWidth(),
           value = text,
           onValueChange = {
               onTextChange(it)
                           },
           placeholder =  {
               Text(text ="Search here ...", modifier = Modifier.alpha(ContentAlpha.medium), color = Color.White)
                          },
           textStyle = TextStyle(fontSize = MaterialTheme.typography.subtitle1.fontSize),
           singleLine = true,
           leadingIcon = {
               IconButton(modifier = Modifier.alpha(ContentAlpha.medium),onClick = {  }) {
                   Icon(imageVector = Icons.Default.Search, contentDescription = "Search Icon", tint = Color.White) }
                         },
           trailingIcon ={
               IconButton(onClick = {
                   if(text.isNotEmpty()){
                       onTextChange("")
                   }
                   else
                   {
                       onCloseClicked()
                   }
               }) {
                   Icon(imageVector = Icons.Default.Close, contentDescription = "Close Icon", tint = Color.White)
               }
                         },
           keyboardOptions = KeyboardOptions(
               keyboardType = KeyboardType.Text,
               imeAction = ImeAction.Search
           ),
           keyboardActions = KeyboardActions(
               onSearch = {
                   onSearchClicked(text)
               }
           ),
           colors = TextFieldDefaults.textFieldColors(
               backgroundColor = Color.Transparent, cursorColor = Color.White.copy(alpha = ContentAlpha.medium)
           )


       )
    }
}

@Composable
fun DefaultAppBar(onSearchClick:()->Unit){
    TopAppBar(
        title = { Text(text = "Home") },
        actions = {
            IconButton(onClick = { onSearchClick()}) {
                Icon(imageVector = Icons.Default.Search, contentDescription = "Search Icon" , tint = Color.White)
            }
        }
    )
}

