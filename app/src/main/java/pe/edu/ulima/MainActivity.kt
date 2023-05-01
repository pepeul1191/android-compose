package pe.edu.ulima

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pe.edu.ulima.ui.app.uis.PokemonDetailScreen
import pe.edu.ulima.ui.login.uis.LoginScreen
import pe.edu.ulima.ui.app.uis.PokemonScreen
import pe.edu.ulima.ui.app.uis.TouchScreen
import pe.edu.ulima.ui.app.viewmodels.PokemonDetailViewModel
import pe.edu.ulima.ui.login.uis.SplashScreen
import pe.edu.ulima.ui.login.viewmodels.LoginScreenViewModel
import pe.edu.ulima.ui.theme.*

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var loginScreenViewModel = LoginScreenViewModel()
        setContent {
            ProgramMovilTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    //SplashScreen()
                    LoginScreen(loginScreenViewModel)
                    //PokemonScreen()
                    //PokemonDetailScreen(viewModel = PokemonDetailViewModel())
                    //TouchScreen()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ProgramMovilTheme {
        Greeting("Android")
    }
}