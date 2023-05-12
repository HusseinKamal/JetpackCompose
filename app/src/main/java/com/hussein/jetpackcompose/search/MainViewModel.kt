package com.hussein.jetpackcompose.search

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
   private val _searchWidgetState:MutableState<SearchWidgetStates> = mutableStateOf( value = SearchWidgetStates.CLOSED)

    var searchWidgetState: State<SearchWidgetStates> = _searchWidgetState

    private val _searchTextState:MutableState<String> = mutableStateOf( value = "")

    var searchTextState: State<String> = _searchTextState

    fun updateSearchWidgetState(newValue : SearchWidgetStates){
        _searchWidgetState.value = newValue
    }
    fun updateSearchTextState(newValue : String){
        _searchTextState.value = newValue
    }


}