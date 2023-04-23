package pe.edu.ulima.ui.app.uis

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import pe.edu.ulima.R
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import pe.edu.ulima.ui.app.viewmodels.PokemonDetailViewModel

@Preview
@Composable
@RequiresApi(Build.VERSION_CODES.P)
public fun PokemonDetailScreenPreview(){
    PokemonDetailScreen(
        PokemonDetailViewModel()
    )
}

@SuppressLint("UnrememberedMutableState")
@Composable
@RequiresApi(Build.VERSION_CODES.P)
public fun PokemonDetailScreen(
    viewModel: PokemonDetailViewModel,
){
    val context = LocalContext.current as Activity
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    val bitmap = remember { mutableStateOf<Bitmap?>(null)}

    var uri2 by remember { mutableStateOf(viewModel.uri) }

    val name: String by viewModel.name.observeAsState(initial = "")

    val uri3: Uri? by viewModel.uri2.observeAsState(initial = null)

    val launcher = rememberLauncherForActivityResult(
        // ActivityResultContracts.StartActivityForResult(),
        contract = ActivityResultContracts.GetContent()
    ) {
            uri: Uri? ->
                if (uri != null){
                    viewModel.updateUri2(uri)
            }
    }

    Column(){
        uri3?.let {
            if(Build.VERSION.SDK_INT < 24){
                bitmap.value = MediaStore.Images.Media.getBitmap(context.contentResolver, it)
            }else{
                val source = ImageDecoder.createSource(context.contentResolver, it)
                bitmap.value = ImageDecoder.decodeBitmap(source)
            }
            viewModel.setUri(uri3!!)
        }
        if (bitmap.value == null){
            Image(
                painter = painterResource(id = R.drawable.ic_default_image),
                contentDescription = null,
                modifier = Modifier
                    .size(400.dp)
                    .padding(20.dp)
            )
        }else{
            Image(
                bitmap = bitmap.value!!.asImageBitmap(),
                contentDescription = null,
                modifier = Modifier
                    .size(400.dp)
                    .padding(20.dp)
            )
        }
        Button(
            onClick = {
                launcher.launch("image/*")
            }
        ) {
            Text(text = "Take a picture")
        }
        TextField(
            value = name,
            onValueChange = {
                viewModel.updateName(it)
            },
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text(text = "Nombre del Pokemon")
            },
            placeholder = {
                Text(text= "")
            },
            singleLine = true,
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent
            )
        )

        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, bottom = 10.dp),
            thickness = 2.dp,
        )
        Button(
            onClick = {
                Log.d("POKEMON_DETAIL_SCREEN", viewModel.uri2.value.toString())
                Log.d("POKEMON_DETAIL_SCREEN", viewModel.name.value.toString())
            }
        ) {
            Text(text = "Ver Modelo")
        }
    }
}