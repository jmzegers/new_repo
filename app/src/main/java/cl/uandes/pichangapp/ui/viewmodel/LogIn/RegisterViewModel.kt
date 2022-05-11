package cl.uandes.pichangapp.ui.viewmodel.LogIn

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import cl.uandes.pichangapp.data.models.DataProfile
import cl.uandes.pichangapp.ui.views.ProfileUser
import cl.uandes.pichangapp.ui.views.appenderUser

class RegisterViewModel (application: Application): AndroidViewModel(application) {
    public fun emailDoesntExist(email: String): Boolean {
        ProfileUser.find { it.email == email } ?: return true
        return false
    }

    public fun registerUser(Team: String,Email: String, Password: String, Category: String, Id: Int) {
        print(ProfileUser)
        ProfileUser = appenderUser(
            ProfileUser,
            DataProfile(nameTeam = Team, email = Email, password = Password, category = Category, id = Id)
        )
        print(ProfileUser)
    }

}