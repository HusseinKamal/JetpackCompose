package com.hussein.jetpackcompose.pagingapp.screens.common

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import com.hussein.jetpackcompose.R
import com.hussein.jetpackcompose.pagingapp.model.UnsplashImage
import com.hussein.jetpackcompose.pagingapp.model.Urls
import com.hussein.jetpackcompose.pagingapp.model.User
import com.hussein.jetpackcompose.pagingapp.model.UserLinks

@Composable
@ExperimentalCoilApi
fun ListContent(items:LazyPagingItems<UnsplashImage>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(all = 12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
        )
    {
        items(
        count = items.itemCount,
        key = items.itemKey(key = { unsplashImage->
    unsplashImage.id
}
        ),
        contentType = items.itemContentType(
            )
    ) { index ->
        val item = items[index]
        item?.let { UnsplashItem(unsplashImage = it) }
        }
    }
}

@Composable
@ExperimentalCoilApi
fun UnsplashItem(unsplashImage: UnsplashImage){
    val context= LocalContext.current

    val painter=  rememberAsyncImagePainter(
        model = ImageRequest.Builder(context)
            .data(unsplashImage.url.regular)
            .crossfade(true)
            .placeholder(R.drawable.ic_placeholder)
            .error(R.drawable.ic_placeholder)
            .build(),
    )

    Box(modifier= Modifier
        .clickable {
            val browseIntent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://api.unsplash.com/@${unsplashImage.user?.username}?utm_source=DemoApp&utm_medium=referral")
            )
            startActivity(context, browseIntent, null)
        }
        .height(300.dp)
        .fillMaxWidth(), contentAlignment = Alignment.BottomCenter){
        
        Image(modifier = Modifier.fillMaxSize(),painter = painter, contentScale = ContentScale.Crop, contentDescription = "Unspalsh Image")
        
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
                .alpha(ContentAlpha.medium),
            color = Color.Black) {
        }
        Row(modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
            .padding(horizontal = 6.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Text(text = buildAnnotatedString {
                append("Photo by ")
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append(unsplashImage.user?.username)
                }
                append(" on Unsplash")
            },
                color=Color.White,
                maxLines = 1,
                fontSize = MaterialTheme.typography.caption.fontSize,
                overflow = TextOverflow.Ellipsis,
            )
            LikeCounter(modifier = Modifier.weight(3f),
            painter= painterResource(id = R.drawable.ic_fav),
            likes= "${unsplashImage.likes}")
        }

    }
}

@Composable
@ExperimentalCoilApi
fun LikeCounter(modifier: Modifier,painter: Painter,likes:String){
    Row(
        modifier = modifier.fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End
    ){
        Icon(painter = painter, contentDescription = "Like Icon", tint = Color.Red)
        Divider(Modifier.width(6.dp))
        Text(
            text = likes,
            color = Color.White,
            fontSize = MaterialTheme.typography.subtitle1.fontSize,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            )
    }
}
@ExperimentalCoilApi
@Composable
@Preview(showBackground = true)
fun UnsplashImagePreview() {
    UnsplashItem(
        unsplashImage = UnsplashImage(
            id = "1",
            url = Urls(regular = ""),
            likes = 100,
            user = User(username = "Stevdza-San", userLinks = UserLinks(html = ""))
        )
    )
}