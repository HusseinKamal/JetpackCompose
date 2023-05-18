package com.hussein.jetpackcompose.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.google.accompanist.permissions.*

@ExperimentalPermissionsApi
@Composable
fun RequestPermission(
    permission:String,
    deniedMessage:String="Give this app permission to continue work",
    rationalMessage:String="To use this app ,you need to give us the permission"
){
    val permissionState= rememberPermissionState(permission = permission)

    HandleRequest(
        permissionState = permissionState,
        deniedContent = {ShouldShowRational->
           PermissionDeniedContent(
               deniedMessage =deniedMessage,
               rationalMessage= rationalMessage,
               shouldShowRational = ShouldShowRational,
               onRequestPermission={ permissionState.launchPermissionRequest()}
           )
            
        },
        content= {
            Content(text = "PERMISSION GRANTED!",showButton = false) {

            }
        }
    )

}

@ExperimentalPermissionsApi
@Composable
fun HandleRequest(
    permissionState: PermissionState,
    deniedContent : @Composable (Boolean) -> Unit,
    content : @Composable () -> Unit,
){
    when(permissionState.status){
        is PermissionStatus.Granted->
        {
            content()
        }
        is PermissionStatus.Denied->
        {
            deniedContent(permissionState.status.shouldShowRationale)
        }
    }
}
@ExperimentalPermissionsApi
@Composable
fun Content(
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
fun PermissionDeniedContent(
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