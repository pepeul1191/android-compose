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
}