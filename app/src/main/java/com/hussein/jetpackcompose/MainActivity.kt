@file:OptIn(ExperimentalFoundationApi::class)

package com.hussein.jetpackcompose

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.graphics.Color.parseColor
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.DisableSelection
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.paging.ExperimentalPagingApi
import coil.annotation.ExperimentalCoilApi
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import coil.transform.RoundedCornersTransformation
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.hussein.jetpackcompose.OnboardingApp.navigation.SetupNavGraphOnboarding
import com.hussein.jetpackcompose.OnboardingApp.viewmodel.SplashViewModel
import com.hussein.jetpackcompose.component.*
import com.hussein.jetpackcompose.component.webview.MainScreenWebView
import com.hussein.jetpackcompose.navigationroot.graphs.RootNavigationGraph
import com.hussein.jetpackcompose.pagingapp.navigation.NavGraphPaging
import com.hussein.jetpackcompose.search.MainViewModel
import com.hussein.jetpackcompose.stopwatch.StopWatch
import com.hussein.jetpackcompose.stopwatch.service.StopwatchService
import com.hussein.jetpackcompose.textanimation.TextAnimScreen
import com.hussein.jetpackcompose.ui.theme.JetpackComposeTheme
import com.hussein.jetpackcompose.ui.theme.Typography
import dagger.hilt.android.AndroidEntryPoint
import java.lang.reflect.Array.get
import javax.inject.Inject

@ExperimentalPagingApi
@ExperimentalCoilApi
@ExperimentalAnimationApi
@ExperimentalPagerApi
@ExperimentalPermissionsApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    lateinit var navHostControl: NavHostController

    private val mainViewModel:MainViewModel by viewModels()

    @Inject
    lateinit var splashViewModel: SplashViewModel

  /*  private var isBound by mutableStateOf(false)
    private lateinit var stopwatchService: StopwatchService
    private val connection = object : ServiceConnection {
        override fun onServiceConnected(className: ComponentName, service: IBinder) {
            val binder = service as StopwatchService.StopwatchBinder
            stopwatchService = binder.getService()
            isBound = true
        }

        override fun onServiceDisconnected(arg0: ComponentName) {
            isBound = false
        }
    }

    override fun onStart() {
        super.onStart()
        Intent(this, StopwatchService::class.java).also { intent ->
            bindService(intent, connection, Context.BIND_AUTO_CREATE)
        }
    }

    private fun requestPermissions(vararg permissions: String) {
        val requestPermissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { result ->
            result.entries.forEach {
                Log.d("MainActivity", "${it.key} = ${it.value}")
            }
        }
        requestPermissionLauncher.launch(permissions.asList().toTypedArray())
    }

    override fun onStop() {
        super.onStop()
        unbindService(connection)
        isBound = false
    }*/
    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().setKeepOnScreenCondition {
            !splashViewModel.isLoading.value
        }
        setContent {
            var selected by remember {
                mutableStateOf(false)
            }
            var selected2 by remember {
                mutableStateOf(false)
            }

            JetpackComposeTheme { //JetpackComposeTheme -->Compose of AppName+"Theme" word
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background,
                ) {
                    /*Column {
                        Greeting("Android")
                        CustomText(text = "Hussein Kamal")
                    }*/
                    /* Column(modifier = Modifier
                        .fillMaxSize()
                        .width(500.dp)
                        .height(500.dp)
                        .background(Color.LightGray),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        CustomItem(weight = 3f, color =MaterialTheme.colors.primary)
                        CustomItem(weight = 1f, color =MaterialTheme.colors.secondary)
                    }*/
                    /* Row(modifier = Modifier.fillMaxSize(),
                        horizontalArrangement = Arrangement.Start,
                        //verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        CustomItem(weight = 3f, color =MaterialTheme.colors.primary)
                        CustomItem(weight = 1f, color =MaterialTheme.colors.secondary)
                    }*/
                    //CustomTextSelection()
                    //ExpandableCard("My Title")
                    //TextFieldCompose()
                    //GoogleButton(onClicked = {})
                    //PasswordField()

                    /* GradientButton(text = "My Button", textColor = Color.White, gradient = Brush.horizontalGradient(
                        colors = listOf(color1,color2)
                    )) {
                    }*/
                    //CountLimitTextFieldComposable()
                    //CustomProgressComposeExample()

                   /* navHostControl = rememberNavController()
                    SetupNavGraph(navHostController = navHostControl)*/

                    //MainScreen()
                    //SearchMainScreen(viewModel = mainViewModel)

                   /* Column {
                        repeat(6){
                            AnimatedShimmer()
                        }
                    }*/
                   /* Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                        CircularImage()
                    }*/

                    /*
                    //Selectable item compose
                    Column(modifier = Modifier.fillMaxSize().padding(80.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {

                        SelectableItem(selected = selected, title = "Hussein Kamal", onClick = {
                            selected = !selected
                        })
                        Spacer(modifier = Modifier.height(10.dp))
                        SelectableItem(selected = selected, title = "Android Developer", onClick = {
                            selected = !selected
                        })
                    }*/
                    //DscecoupledConstraintLayout()
                    //RequestPermission(permission= android.Manifest.permission.READ_CONTACTS)
                    /*MultipleRequestPermission(permissions = listOf(
                        android.Manifest.permission.READ_CONTACTS,
                        android.Manifest.permission.CAMERA
                    ))*/
                    //Icon(imageVector = Icons.Default.AccessTime , contentDescription = "Icon test" )
                 /*   HyperlinkText(
                        fullText = "By using our services are agreeing to our\n" + "Terms and Privacy statement",
                        hyperLinks = mutableMapOf(
                            "Terms" to "https://google.com",
                            "Privacy statement" to "https://google.com"
                        ),
                        textStyle = TextStyle(
                            textAlign = TextAlign.Center,
                            color = Gray
                        ),
                        linkTextColor = Color.Blue,
                        fontSize = 18.sp
                    )*/
                    //RootNavigationGraph(navController = rememberNavController())
                    //MainScreenWebView()
                    //TextAnimScreen()
                    //ColorPicker()
                    //ImageColorPicker()
                    //LazyList()
                    //AnimationTopBar()
                    //ImagePicker()
                }

                //Change system bar colors
                /*   val system = rememberSystemUiController()
                   val darkTheme= isSystemInDarkTheme()
                   SideEffect {
                       system.setSystemBarsColor(
                           color = if(darkTheme) Color.LightGray else Teal200
                       )
                   }*/
              /*  val screen by splashViewModel.startDestination
                val navController = rememberNavController()
                SetupNavGraphOnboarding(navController = navController, startDestination = screen)*/
                
                //LoadingAnimation()//Add animation loading compose

                //Swipe library --> https://github.com/saket/swipe

                //StopWatch
               /* if (isBound) {
                    StopWatch(stopwatchService = stopwatchService)
                }*/

            }
        }
        //requestPermissions(android.Manifest.permission.POST_NOTIFICATIONS)
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}
@Composable
fun BoxComposable() {
    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center) {//Add white background to internal box
        Box(modifier = Modifier
            .background(Color.Blue)
            //.width(100.dp)
            //.height(100.dp)
            .verticalScroll(rememberScrollState(),)//Remember scroll inside box
        ){
            Box(modifier = Modifier
                .background(Color.Green)
                .width(100.dp)
                .height(100.dp))
            Text(text = "I Love Android", fontSize = 40.sp)
        }
    }
}

@Composable
fun CustomText(text:String){
    Text(
        text = text,
        style = Typography.h5,

    )
}

@Composable
fun CustomizeText(){
    Column(modifier = Modifier.fillMaxSize()
        //verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = stringResource(id =R.string.app_name),//call string from strings file
            modifier = Modifier
                .background(MaterialTheme.colors.primary)
                .padding(16.dp)
                .width(200.dp),
            color = Color.White,
            fontSize = MaterialTheme.typography.h6.fontSize,
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        AnnotatedString()
        CustomizeRepeatText()
    }
}
@Composable
fun AnnotatedString(){//Customize Text with specific chars and text styles
    Text(buildAnnotatedString {
        withStyle(style = SpanStyle(
            color = MaterialTheme.colors.primary,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )){
            append("H")
        }
        append("A")
        append("B")
        append("C")
        append("D")
        append("E")
    })
}
@Composable
fun CustomizeRepeatText(){//Customize Text repeatly with 3(...) dots at end of line
    Text(text = "Hello World".repeat(20), maxLines = 2, overflow = TextOverflow.Ellipsis)
}

@Composable
fun CustomTextSelection(){//Text selection and copy selected text
    SelectionContainer {
        Column {
            Text(text = "Hello World")
            DisableSelection {
                Text(text = "Hello World")
            }
            Text(text = "Hello World")
        }
    }
}

//We used this ColumnScope in this extension function to use weight() from Column Scope
//We used this RowScope in this extension function to use weight() from Row Scope
//Color=MaterialTheme.colors.primary is default Value
@Composable
fun RowScope.CustomItem(weight:Float,color:Color=MaterialTheme.colors.primary){
    Surface(modifier = Modifier
        .width(200.dp)
        .height(50.dp) //We should add height in Row
        .weight(weight),
        color = color) {
    }
}

@Composable
fun SuperScriptText(normalText:String,
                    superText:String,
                    normalTextSize:TextUnit=MaterialTheme.typography.subtitle1.fontSize,
                    superTextSize:TextUnit=MaterialTheme.typography.overline.fontSize){
    Text(buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                fontSize = normalTextSize,

            )
        ){
            append(normalText)
        }
        withStyle(
            style = SpanStyle(
                fontSize = superTextSize,
                fontWeight = FontWeight.Normal,
                baselineShift = BaselineShift.Superscript)
        ){
            append(superText)
        }
    })
}
@Composable
fun TextFieldCompose(){//Text Field like Edit Text
    Column(modifier = Modifier.fillMaxSize(),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center) {
        var text by remember{ mutableStateOf("Type here ...") }
        TextField(value = text, onValueChange = { newText->
            text=newText

        },
        enabled = false,//read only can cpy value inside text but not edit it
        readOnly = false,
        label = {
            Text("Title") //add hint inside edit text like text input layout
        },
        singleLine = false,
        maxLines = 2,
        leadingIcon = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Filled.Email, contentDescription = "Email Icon")
            }
        },
        trailingIcon = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Filled.Check, contentDescription = "Check Icon")
            }
        },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Go
            ),
            keyboardActions = KeyboardActions(
                onSearch = {

                }
            )
        )
        Box(modifier = Modifier
            .width(40.dp)
            .height(40.dp))
        OutlinedTextField(value = text, onValueChange = { newText->
            text=newText

        },
            enabled = false,//read only can cpy value inside text but not edit it
            readOnly = false,
            label = {
                Text("Title") //add hint inside edit text like text input layout
            },
            singleLine = false,
            maxLines = 2,
            leadingIcon = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Filled.Email, contentDescription = "Email Icon")
                }
            },
            trailingIcon = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Filled.Check, contentDescription = "Check Icon")
                }
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Go
            ),
            keyboardActions = KeyboardActions(
                onSearch = {

                }
            )
        )
        Box(modifier = Modifier
            .width(40.dp)
            .height(40.dp))

        BasicTextField(modifier = Modifier
            .background(Color.Gray)
            .padding(16.dp),value = text, onValueChange = { newText->
            text=newText

        },
            enabled = false,//read only can cpy value inside text but not edit it
            readOnly = false,
            /*label = {
                Text("Title") //add hint inside edit text like text input layout
            },
            singleLine = false,
            maxLines = 2,
            leadingIcon = {
                IconButton(onClick = { *//*TODO*//* }) {
                    Icon(imageVector = Icons.Filled.Email, contentDescription = "Email Icon")
                }
            },
            trailingIcon = {
                IconButton(onClick = { *//*TODO*//* }) {
                    Icon(imageVector = Icons.Filled.Check, contentDescription = "Check Icon")
                }
            },*/
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Go
            ),
            keyboardActions = KeyboardActions(
                onSearch = {

                }
            )
        )
    }
}

@Composable
fun CoilImage(){
    Box(modifier = Modifier
        .width(150.dp)
        .height(150.dp), contentAlignment = Alignment.Center) {
        //This deprecated so we will use rememberAsyncImagePainter
       /* val painter=rememberImagePainter(data = "https://pngimg.com/uploads/mario/mario_PNG125.png", builder = {
            placeholder(R.drawable.ic_launcher_background)
            error(R.drawable.ic_error)
            crossfade(100)
        })*/
        val painter = rememberAsyncImagePainter(
            ImageRequest.Builder(LocalContext.current)
                .data(data = "https://pngimg.com/uploads/mario/mario_PNG125.png")
                .apply(block = fun ImageRequest.Builder.() {
                    placeholder(R.drawable.ic_launcher_background)
                    error(R.drawable.ic_error)
                    crossfade(1000)
                    transformations(
                        CircleCropTransformation(),
                        RoundedCornersTransformation(),
                        RoundedCornersTransformation(50f)
                    )
                }).build()
        )
        val painterState=painter.state
        Image(painter = painter, contentDescription = "Nature Image")
        //ImagePainter deprecated so we will use AsyncImagePainter
        if(painterState is AsyncImagePainter.State.Loading){
            CircularProgressIndicator()
        }

    }
}

@Composable
fun CustomProgressComposeExample(){
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {

        var value by remember {
            mutableStateOf(0)
        }
        CustomProgressComponent(
            indicatorValue = value,
        )
        TextField(
            value = value.toString(), onValueChange = {
                value = if (it.isNotEmpty()) {
                    it.toInt()
                } else {
                    0
                }
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
    }
}
@Composable
fun PasswordField(){
    Column(modifier = Modifier.fillMaxSize(),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally) {
        var password by rememberSaveable{
            mutableStateOf("")
        }
        var visible by remember{
            mutableStateOf(false)
        }
        val icon=if(visible) painterResource(id = R.drawable.ic_visibility) else painterResource(id = R.drawable.ic_visibility_off)
        OutlinedTextField(value = password, onValueChange = { newText->
            password= newText
         }, placeholder = {Text(text = "Password")},
            label = {Text(text = "Password")},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingIcon = {
                IconButton(onClick = {
                    visible=!visible
                }) {
                    Icon(painter = icon, contentDescription ="Password Visibility" )
                }
            },
            visualTransformation = if(visible) VisualTransformation.None else PasswordVisualTransformation()
        )
    }
}

@Composable
fun LazyColumnComposable() {
    val section= listOf("A","B","C","D","E","F","G","H")
    LazyColumn(contentPadding = PaddingValues(all = 12.dp), verticalArrangement =Arrangement.spacedBy(12.dp) ){
        section.forEach{ section->
            stickyHeader {
                Text(modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Gray)
                    .padding(12.dp),text = "Section $section")
            }
            items(10){
                Text(modifier = Modifier.padding(12.dp),
                    text = "Items $it from the section $section")
            }
        }
    }
}
@Composable
fun CountLimitTextFieldComposable() {
  Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
     var name by remember {
         mutableStateOf("")
     }
      val maxChars=10
      OutlinedTextField(
          value = name,
          onValueChange = {
              if(it.length <= maxChars){
                  name =it
              }
                          } ,
          label = { Text(text = "Name")},
          placeholder = { Text(text = "Enter your name")},
          maxLines = 1
      )
      
  }
}

//To show design in split screen which is annotated with @Preview
@OptIn(ExperimentalPagingApi::class)
@ExperimentalMaterialApi
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetpackComposeTheme {
      /*  Column {
            Greeting("Android")
            CustomText(text = "Hussein Kamal")
        }*/
       /* Column(modifier = Modifier.fillMaxSize().height(500.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            //verticalArrangement = Arrangement.SpaceBetween
        ) {
            CustomItem(weight = 3f, color =MaterialTheme.colors.primary)
            CustomItem(weight = 1f, color =MaterialTheme.colors.secondary)
        }*/
       /* Row(modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            CustomItem(weight = 3f, color =MaterialTheme.colors.primary)
            CustomItem(weight = 1f, color =MaterialTheme.colors.secondary)
        }*/
        //BoxComposable()
        //CustomizeText()
        /*Column(modifier = Modifier.fillMaxSize()
        ) {
            SuperScriptText(normalText = "Hello", superText = "World")
        }*/
        //ExpandableCard("My Title")
        //TextFieldCompose()
        //PasswordField()
        //LazyColumnComposable()
        //CountLimitTextFieldComposable()
        //CustomProgressComposeExample()
      /*  val navController = rememberNavController()
        NavGraphPaging(navHostController = navController)*/
        ColorPicker()
    }
}
val String.color
    get()= Color(parseColor(this))