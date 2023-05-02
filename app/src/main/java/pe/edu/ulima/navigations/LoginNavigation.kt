package pe.edu.ulima.navigations

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import pe.edu.ulima.ui.login.uis.LoginScreen
import pe.edu.ulima.ui.login.viewmodels.LoginScreenViewModel

sealed class Screen(val route: String){
    object LoginScreen : Screen("login_screen")
}

@Composable
fun LoginNavigation(
    loginScreenViewModel: LoginScreenViewModel
){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.LoginScreen.route
    ){
        composable(route = Screen.LoginScreen.route){
            LoginScreen(loginScreenViewModel)
        }
    }
}
