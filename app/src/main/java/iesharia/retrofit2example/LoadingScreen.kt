package iesharia.retrofit2example

import android.os.Build
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest

@Composable
fun LoadingScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Cargando...",
            fontSize = 22.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(bottom = 24.dp)
        )
        LoadingGif()
    }
}

@Composable
fun LoadingGif(
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val imageLoader = ImageLoader.Builder(context)
        .components {
            when {
                Build.VERSION.SDK_INT >= 28 -> add(ImageDecoderDecoder.Factory())
                else -> add(GifDecoder.Factory())
            }
        }
        .build()

    Image(
        painter = rememberAsyncImagePainter(
            ImageRequest.Builder(context)
                .data(R.drawable.loading)
                .size(350)
                .build(),
            imageLoader = imageLoader
        ),
        contentDescription = "Cargando...",
        modifier = modifier.fillMaxWidth()
    )
}
