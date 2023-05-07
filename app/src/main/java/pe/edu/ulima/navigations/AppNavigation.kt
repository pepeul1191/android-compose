package pe.edu.ulima.navigations

import android.util.Log
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import pe.edu.ulima.ui.app.uis.PokemonDetailScreen
import pe.edu.ulima.ui.app.uis.PokemonScreen
import pe.edu.ulima.ui.app.viewmodels.PokemonDetailViewModel
import pe.edu.ulima.ui.app.viewmodels.PokemonViewModel

@Composable
fun AppNavigation(
    pokemonScreenModel: PokemonViewModel,
    pokemonDetailViewModel: PokemonDetailViewModel
){
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val pokemonIdParam = navBackStackEntry?.arguments?.getInt("pokemon_id")

    NavHost(
        navController = navController,
        startDestination = "/pokemon"
    ){
        // vista para mostrar el listado de pokemones
        composable(
            route = "/pokemon"
        ){
           PokemonScreen(
               viewModel = pokemonScreenModel,
               navController
           )
        }
        // vista para mostrar otra cosa
        composable(
            route = "/pokemon/detalle?pokemon_id={pokemon_id}",
            arguments = listOf(
                navArgument("pokemon_id"){
                    type = NavType.IntType
                    defaultValue = 0
                }
            )
        ){
            Log.d("APP_NAVIGATION", pokemonIdParam.toString())
            pokemonDetailViewModel.getPokemon(pokemonIdParam!!)
            PokemonDetailScreen(
                viewModel = pokemonDetailViewModel
            )
        }
    }
}