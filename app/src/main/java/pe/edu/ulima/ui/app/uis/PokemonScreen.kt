package pe.edu.ulima.ui.app.uis

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import pe.edu.ulima.R
import pe.edu.ulima.ui.theme.Gray200
import pe.edu.ulima.ui.theme.Orange200

@Preview
@Composable
public fun PokemonScreenPreview(){
    PokemonScreen()
}

data class Pokemon(
    var nombre: String = "",
    var peso: Float = 0F,
    var talla: Float = 0F,
    var url: String = "",
)

@Composable
public fun PokemonScreen(){
    var tmpPokmeonList = listOf(
        Pokemon("BULBASAUR", 12F, 23F, "https://pokefanaticos.com/pokedex/assets/images/pokemon_imagenes/1.png"),
        Pokemon("IVYSAUR", 12F, 23F, "https://pokefanaticos.com/pokedex/assets/images/pokemon_imagenes/2.png"),
        Pokemon("VENUSAUR", 12F, 23F, "https://pokefanaticos.com/pokedex/assets/images/pokemon_imagenes/3.png"),
        Pokemon("VENUSAUR", 12F, 23F, "https://pokefanaticos.com/pokedex/assets/images/pokemon_imagenes/3.png"),
        Pokemon("VENUSAUR", 12F, 23F, "https://pokefanaticos.com/pokedex/assets/images/pokemon_imagenes/3.png"),
        Pokemon("VENUSAUR", 12F, 23F, "https://pokefanaticos.com/pokedex/assets/images/pokemon_imagenes/3.png"),
        Pokemon("VENUSAUR", 12F, 23F, "https://pokefanaticos.com/pokedex/assets/images/pokemon_imagenes/3.png"),
    )

    Column(){
        for(i in 1..4){
            Row(
                modifier = Modifier.fillMaxWidth()
            ){
                Image(
                    painter = rememberImagePainter(data = "https://pokefanaticos.com/pokedex/assets/images/pokemon_imagenes/1.png"),
                    contentDescription = "Logo Ulima",
                    modifier = Modifier
                        .size(80.dp)
                        .padding(bottom = 10.dp),
                )
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .align(Alignment.CenterVertically)
                ){
                    Text("Nombre: BULBASAUR")
                    Text("Peso: 6.9 kg")
                }
            }
        }
        Divider(
            modifier = Modifier.fillMaxWidth().padding(top = 10.dp, bottom = 10.dp),
            thickness = 2.dp,
        )
        var pokemones: ArrayList<Pokemon> = ArrayList(tmpPokmeonList)
        for (pokemon in pokemones) {
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
                }
            }
        }
    }
}