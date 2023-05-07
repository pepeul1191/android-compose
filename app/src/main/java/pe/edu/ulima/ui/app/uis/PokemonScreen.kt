package pe.edu.ulima.ui.app.uis

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import pe.edu.ulima.models.Pokemon
import pe.edu.ulima.ui.app.viewmodels.PokemonViewModel
import pe.edu.ulima.ui.theme.Orange200

@Preview
@Composable
public fun PokemonScreenPreview(){
    /*PokemonScreen(
        PokemonViewModel(),
    )*/
}

@Composable
public fun PokemonScreen(
    viewModel: PokemonViewModel,
    navController: NavHostController
){
    viewModel.setPokemons()
    LazyVerticalGrid(
        modifier = Modifier.fillMaxSize(),
        columns = GridCells.Fixed(5),
        content = {
            items(viewModel.pokemons!!.size){ i ->
                val pokemon: Pokemon = viewModel.pokemons!![i]
                Image(
                    painter = rememberImagePainter(data = pokemon.url),
                    contentDescription = pokemon.nombre,
                    modifier = Modifier
                        .size(100.dp)
                        .padding(bottom = 10.dp)
                        .clickable {
                            Log.d("POKEMON_SCREEN", pokemon.id.toString())
                            navController.navigate("/pokemon/detalle?pokemon_id=${pokemon.id.toString()}")
                        },
                )
            }
        }
    )
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomEnd
    ) {
        FloatingActionButton(
            onClick = {
                navController.navigate("/pokemon/new")
                      },
            content = {
                Icon(
                    Icons.Default.Add,
                    contentDescription = null
                ) },
            backgroundColor = Orange200,
            modifier = Modifier.padding(20.dp)
        )
    }
}

