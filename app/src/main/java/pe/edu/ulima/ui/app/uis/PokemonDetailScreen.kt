package pe.edu.ulima.ui.app.uis

import android.content.ContentValues
import android.content.Intent
import android.graphics.Bitmap
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.R
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.drawable.toBitmap
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.ImageLoader
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import coil.request.SuccessResult
import kotlinx.coroutines.launch
import pe.edu.ulima.ui.app.viewmodels.PokemonDetailViewModel
import pe.edu.ulima.R as RLocal

@Composable
public fun PokemonDetailScreen(
    viewModel: PokemonDetailViewModel
){
    // viewmodel
    val nombre: String by viewModel.name.observeAsState("")
    val url: String by viewModel.url.observeAsState("")
    val peso: Float by viewModel.peso.observeAsState(0f)
    val talla: Float by viewModel.talla.observeAsState(0f)
    val titulo: String by viewModel.titulo.observeAsState("")

    Column() {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                text =titulo.toUpperCase(),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(16.dp),
                fontSize = 24.sp
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth()
        ){
            Image(
                painter = rememberImagePainter(data = url),
                contentDescription = nombre,
                modifier = Modifier
                    .size(80.dp)
                    .padding(bottom = 10.dp),
            )
            Column(
                modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically)
            ){
                TextField(
                    value = nombre,
                    onValueChange = {
                        viewModel.updateName(it)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    label = {
                        Text(text = "Nombre")
                    },
                    placeholder = {
                        Text(text= "")
                    },
                    singleLine = true,
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.Transparent
                    )
                )
                TextField(
                    value = "${peso.toString()} kg" ,
                    onValueChange = {
                        viewModel.updatePeso(it.toFloat())
                    },
                    modifier = Modifier.fillMaxWidth(),
                    label = {
                        Text(text = "Peso Kg")
                    },
                    placeholder = {
                        Text(text= "")
                    },
                    singleLine = true,
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.Transparent
                    )
                )
                TextField(
                    value = "${talla.toString()} m" ,
                    onValueChange = {
                        viewModel.updateTalla(it.toFloat())
                    },
                    modifier = Modifier.fillMaxWidth(),
                    label = {
                        Text(text = "Talla en metros")
                    },
                    placeholder = {
                        Text(text= "")
                    },
                    singleLine = true,
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.Transparent
                    )
                )
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 15.dp/*, start = 40.dp, end = 40.dp*/), // start -> izquierda, end -> derecha
                    onClick = {

                    }
                ){
                    Text(
                        "${ titulo.toUpperCase().split(" ")[0] } REGISTRO"
                    )
                }
                // shares buttons
                val context = LocalContext.current
                val launcher = rememberLauncherForActivityResult(
                    contract = ActivityResultContracts.StartActivityForResult(),
                    onResult = {
                        println("COMPARTIDOOOOOOOOOOOOO")
                    }
                )
                val coroutineScope = rememberCoroutineScope()
                // facebook
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 1.dp, /*start = 40.dp, end = 40.dp*/),
                    onClick = {
                        val intent = Intent(Intent.ACTION_SEND)
                        intent.type = "text/plain"
                        val appPackageName = "com.facebook.katana"
                        intent.putExtra(Intent.EXTRA_TITLE, "Has selecinado un $nombre".toUpperCase())
                        intent.putExtra(Intent.EXTRA_TEXT, url)
                        intent.setPackage(appPackageName)
                        if (intent.resolveActivity(context.packageManager) != null) {
                            launcher.launch(intent)
                        }
                    },
                    //colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF4CAF50)) ,
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Blue)
                ){
                    Image(
                        painter = painterResource(id = RLocal.drawable.ic_facebook),
                        contentDescription = "Logo Facebook",
                        modifier = Modifier
                            .size(22.dp)
                            .padding(end = 10.dp),
                        colorFilter = ColorFilter.tint(Color.White)
                    )
                    Text(
                        "Compartir en Facebook",
                        color = Color.White
                    )
                }
                // whastapp
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 1.dp, /*start = 40.dp, end = 40.dp*/),
                    onClick = {
                        coroutineScope.launch {
                            val imageLoader = ImageLoader(context)
                            val request = ImageRequest.Builder(context)
                                .data(url)
                                .build()
                            val bitmap = (imageLoader.execute(request) as SuccessResult).drawable.toBitmap()
                            val uri = context.contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, ContentValues())?.apply {
                                context.contentResolver.openOutputStream(this).use {
                                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, it)
                                }
                            }
                            val intent = Intent(Intent.ACTION_SEND)
                            intent.type = "image/jpeg"
                            val appPackageName = "com.whatsapp"
                            intent.putExtra(Intent.EXTRA_TITLE, "Has selecinado un $nombre".toUpperCase())
                            intent.putExtra(Intent.EXTRA_STREAM, uri)
                            intent.putExtra(Intent.EXTRA_TEXT, "Has selecinado un ${nombre.toUpperCase()}")
                            intent.setPackage(appPackageName)
                            if (intent.resolveActivity(context.packageManager) != null) {
                                launcher.launch(intent)
                            }
                        }
                    },
                    //colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF4CAF50)) ,
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Green)
                ){
                    Image(
                        painter = painterResource(id = RLocal.drawable.ic_whatsapp),
                        contentDescription = "Logo Whatsapp",
                        modifier = Modifier
                            .size(22.dp)
                            .padding(end = 10.dp),
                        colorFilter = ColorFilter.tint(Color.White)
                    )
                    Text(
                        "Compartir en WhastApp",
                        color = Color.White
                    )
                }
            }
        }
    }
}