package pe.edu.ulima.ui.app.viewmodels

import android.net.Uri
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import pe.edu.ulima.R
import pe.edu.ulima.services.pokemonsList
import java.io.File

class PokemonDetailViewModel: ViewModel() {
    private val _uri = mutableStateOf<Uri?>(
     null
    )
    val uri get() = _uri.value
    fun setUri(uri: Uri) {
        _uri.value = uri
    }

    private val _name = MutableLiveData<String>("")
    var name: LiveData<String> = _name
    fun updateName(it: String){
        _name.postValue(it)
    }

    private val _uri2 = MutableLiveData<Uri>(null)
    var uri2: LiveData<Uri> = _uri2
    fun updateUri2(it: Uri){
        _uri2.postValue(it)
    }

    private val _generationName = MutableLiveData<String>("")
    var generationName: LiveData<String> = _generationName
    fun updateGenerationName(it: String){
        _generationName.postValue(it)
    }

    private val _urlImage = MutableLiveData<String>("")
    var urlImage: LiveData<String> = _urlImage
    fun updateUrlImage(it: String){
        _urlImage.postValue(it)
    }

    fun getPokemon(id: Int){
        for (pokemon in pokemonsList!!) {
            if (pokemon.id == id){
                this.updateName(pokemon.nombre)
                this.updateUrlImage(pokemon.url)
            }
        }
    }
}