package pe.edu.ulima.ui.app.uis

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import pe.edu.ulima.ui.app.viewmodels.PokemonViewModel

@Preview
@Composable
public fun PokemonScreenPreview(){
    PokemonScreen(
        PokemonViewModel(),
        goToPokemonDetail = {}
    )
}

@Composable
public fun PokemonScreen(
    viewModel: PokemonViewModel,
    goToPokemonDetail: () -> Unit
){
    viewModel.setPokemons()
    Column(){
        for (pokemon in viewModel.pokemons!!) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ){
                Image(
                    painter = rememberImagePainter(data = pokemon.url),
                    contentDescription = pokemon.nombre,
                    modifier = Modifier
                        .size(80.dp)
                        .padding(bottom = 10.dp),
                )
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .align(Alignment.CenterVertically)
                ){
                    Text("Nombre: ${pokemon.nombre}")
                    Text("Peso: ${pokemon.peso} kg")
                    Text("Tall: ${pokemon.talla} m")
                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 15.dp/*, start = 40.dp, end = 40.dp*/), // start -> izquierda, end -> derecha
                        onClick = {
                            viewModel.setSelectedId(pokemon.id)
                            goToPokemonDetail()
                        }
                    ){
                        Text("VER DETALLE")
                    }
                }
            }
        }
        Row(){
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 15.dp/*, start = 40.dp, end = 40.dp*/), // start -> izquierda, end -> derecha
                onClick = {
                    viewModel.setSelectedId(2)
                    goToPokemonDetail()
                }
            ){
                Text("VER DETALLE EN DURO")
            }
        }
    }
}