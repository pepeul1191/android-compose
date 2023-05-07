package pe.edu.ulima.ui.app.uis

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import pe.edu.ulima.ui.app.viewmodels.PokemonDetailViewModel

@Composable
public fun PokemonDetailScreen(
    viewModel: PokemonDetailViewModel
){
    // viewmodel
    val nombre: String by viewModel.name.observeAsState("")
    val url: String by viewModel.url.observeAsState("")
    val peso: Float by viewModel.peso.observeAsState(0f)
    val talla: Float by viewModel.talla.observeAsState(0f)

    Column() {
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
                Text("Nombre: ${nombre}")
                Text("Peso: ${peso} kg")
                Text("Talla: ${talla} cm")
            }
        }
    }
}