package cl.uandes.pichangapp.ui.viewmodel.LogIn

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cl.uandes.pichangapp.data.models.Users

class LogInViewModel(): ViewModel() {

    private val users = Users.ProfileUser()
    var emailLiveData = MutableLiveData("")
    var passwordLiveData = MutableLiveData("")
    var credentialsAreValid : MutableLiveData<Boolean> = MutableLiveData()


    public fun emailDoesntExist(email: String): Boolean {
        users?.find { it.email == email } ?: return true
        return false
    }

    public fun verifyUser(email: String, password: String) : Boolean {
        val user = users?.find { it.email == email }

        if (user?.password == password) {
            return true
        }
        return false
    }
}