package pe.edu.ulima.ui.login.viewmodels

import android.os.Handler
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel: ViewModel() {
    private val _usuario = MutableLiveData<String>("")
    var usuario: LiveData<String> = _usuario
    fun updateUsuario(it: String){
        _usuario.postValue(it)
    }

    private val _contrasenia = MutableLiveData<String>("")
    var contrasenia: LiveData<String> = _contrasenia
    fun updateContrasenia(it: String){
        _contrasenia.postValue(it)
    }

    private val _mensaje = MutableLiveData<String>("")
    var mensaje: LiveData<String> = _mensaje
    fun updateMensaje(it: String){
        _mensaje.postValue(it)
    }

    fun validar(){
        println("++++++++++++++++++++++++++++")
        println(usuario.value)
        println(contrasenia.value)
        if(usuario.value != "root" && contrasenia.value != "123"){
            updateMensaje("Error: Usuario y contraseña no válidos")
        }else{
            updateMensaje("Todo OK")
        }
        Handler().postDelayed({
            updateMensaje("")
        }, 3000)
        println("++++++++++++++++++++++++++++")
    }
}