package pe.edu.ulima.ui.theme

import android.app.Activity
import android.content.Intent
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import androidx.core.content.FileProvider
import pe.edu.ulima.BuildConfig
import pe.edu.ulima.R

@Preview
@Composable
fun TopBarPreview(){
    TopBar(showBottomSheetDialog = {})
}

@Composable
fun TopBar(showBottomSheetDialog: () -> Unit){
    Box(
        modifier = Modifier
            .height(80.dp)
            .fillMaxWidth()
    ){
        TopAppBar(
            //modifier = Modifier.padding(bottom =  24.dp),
            //backgroundColor = Color(R.color.purple_500),
            elevation = 0.dp,
            title = {
                Text(
                    text = "Aplicación Demo",
                    color = Color.White
                )
            },
            actions = {
                AppBarActions(showBottomSheetDialog)
            }
        )
    }
}

@Composable
fun AppBarActions(showBottomSheetDialog: () -> Unit){
    SearchAction()
    ShareAction(showBottomSheetDialog)
    MoreAction()
}

@Composable
fun SearchAction(){
    val context = LocalContext.current
    IconButton(
        onClick = {
            Toast.makeText(context,"Buscar???", Toast.LENGTH_SHORT).show()
        }
    ){
        Icon(
            imageVector = Icons.Filled.Search,
            contentDescription = "icono buscar",
            tint = Color.White
        )
    }
}

@Composable
fun ShareAction(showBottomSheetDialog: () -> Unit){
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult(),
        onResult = { /* Handle activity result */ }
    )

    val context = LocalContext.current
    var launched = false
    IconButton(
        onClick = {
            showBottomSheetDialog()
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_TEXT, "https://www.ulima.edu.pe/")
            launcher.launch(intent)
            /*

            val instagramPackageName = "com.instagram.android"
            val facebookPackageName = "com.facebook.katana"
            val whatsappPackageName = "com.whatsapp"

            // Share on Instagram
            intent.setPackage(instagramPackageName)
            if (intent.resolveActivity(context.packageManager) != null) {
                launcher.launch(intent)
            }

            // Share on Facebook
            intent.setPackage(facebookPackageName)
            if (intent.resolveActivity(context.packageManager) != null) {
                launcher.launch(intent)
            }

            // Share on WhatsApp
            intent.setPackage(whatsappPackageName)
            if (intent.resolveActivity(context.packageManager) != null) {
                launcher.launch(intent)
            }
            */
        }
    ){
        Icon(
            imageVector = Icons.Filled.Share,
            contentDescription = "icono compartir",
            tint = Color.White
        )
    }
}

@Composable
fun MoreAction(){
    var expanded by remember { mutableStateOf(false)}
    val context = LocalContext.current
    IconButton(
        onClick = {
            expanded = true
        }
    ){
        Icon(
            imageVector = Icons.Filled.MoreVert,
            contentDescription = "icono más",
            tint = Color.White
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            }
        ) {
            DropdownMenuItem(
                onClick = {
                    expanded = false
                    //context.startActivity(Intent(context, UploadActivity::class.java))
                }
            ){
                Text(
                    text = "Editar Perfil"
                )
            }
            DropdownMenuItem(
                onClick = {
                    expanded = false
                    (context as? Activity)?.run {
                        finishAffinity()
                    }
                }
            ){
                Text(
                    text = "Salir"
                )
            }
        }
    }
}
