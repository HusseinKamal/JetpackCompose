package com.hussein.jetpackcompose.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.google.accompanist.permissions.*

@ExperimentalPermissionsApi
@Composable
fun MultipleRequestPermission(
    permissions:List<String>,
    deniedMessage:String="Give this app permission to continue work",
    rationalMessage:String="To use this app ,you need to give us the permission"
){

    val multiplePermissionState = rememberMultiplePermissionsState(permissions = permissions)
    HandleRequests(
        multiplePermissionState = multiplePermissionState,
        deniedContent = {ShouldShowRational->
            PermissionDeniedContents(
                deniedMessage =deniedMessage,
                rationalMessage= rationalMessage,
                shouldShowRational = ShouldShowRational,
                onRequestPermission={ multiplePermissionState.launchMultiplePermissionRequest()}
            )

        },
        content= {
            Contents(text = "PERMISSION GRANTED!",showButton = false) {

            }
        }
    )

}

@ExperimentalPermissionsApi
@Composable
fun HandleRequests(
    multiplePermissionState: MultiplePermissionsState,
    deniedContent : @Composable (Boolean) -> Unit,
    content : @Composable () -> Unit,
){

    var shouldShowRational by remember {
        mutableStateOf(false)
    }
    val result= multiplePermissionState.permissions.all {
        shouldShowRational= it.status.shouldShowRationale
        it.status == PermissionStatus.Granted
    }
    if(result)
    {
        content()
    }
    else
    {
        deniedContent(shouldShowRational)
    }

}
@ExperimentalPermissionsApi
@Composable
fun Contents(
    text: String,
    showButton : Boolean = true,
    onClick : () -> Unit,
){
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(50.dp), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = text, textAlign = TextAlign.Center)
        Spacer(modifier = Modifier.height(12.dp))
        if (showButton)
        {
            Button(onClick = onClick) {
                Text(text  = "Request")
            }
        }

    }
}
@ExperimentalPermissionsApi
@Composable
fun PermissionDeniedContents(
    deniedMessage :String,
    rationalMessage:String,
    shouldShowRational :Boolean,
    onRequestPermission:()->Unit
) {
    if (shouldShowRational) {
        AlertDialog(onDismissRequest = { }, title = {
            Text(
                text = "Permission Request",
                style = TextStyle(
                    fontSize = MaterialTheme.typography.h6.fontSize,
                    fontWeight = FontWeight.Bold
                )
            )
        },
            text = { Text(text = rationalMessage) },
            confirmButton = {
                Button(onClick = onRequestPermission) {
                    Text(text = "Give Permission")
                }
            }
        )
    }
    else
    {
        Content(text = deniedMessage, onClick =onRequestPermission)
    }
}