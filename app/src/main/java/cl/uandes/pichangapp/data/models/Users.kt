package cl.uandes.pichangapp.data.models

class Users {
    companion object {
        fun ProfileUser() : MutableList<DataProfile> {
            val Profiles = ArrayList<DataProfile>()
            Profiles.add(DataProfile(nameTeam = "UANDES", email = "uandes@uandes.cl",
                password = "123", category = "Masculina", id = 1))
            return Profiles.toMutableList()
        }
    }
}