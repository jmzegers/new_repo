package cl.uandes.pichangapp.ui.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import cl.uandes.pichangapp.R
import cl.uandes.pichangapp.databinding.ActivityMainBinding
import cl.uandes.pichangapp.data.models.DataMatch
import cl.uandes.pichangapp.data.models.DataProfile
import cl.uandes.pichangapp.data.models.StructureDataMatch
import cl.uandes.pichangapp.data.models.StructureProfile
import com.google.android.material.bottomnavigation.BottomNavigationView

var contextSituation = ""
var isLoggedIn = false
var elementId = ""
var n = 0


var ProfileUser = arrayOf<DataProfile>(
  DataProfile(nameTeam = "UANDES", email = "uandes@uandes.cl", password = "123", category = "Masculina", id = 1),
  DataProfile(nameTeam = "UDD", email = "a", password = "a", category = "Masculina", id = 2)
)

fun appenderUser(arr: Array<DataProfile>, element: DataProfile): Array<DataProfile> {
  val mutableArray = arr.toMutableList()
  mutableArray.add(element)
  return mutableArray.toTypedArray()
}

var Matchesss = arrayOf<DataMatch>(
  StructureDataMatch("UANDES", teamTwo = "UAI",date ="2022-04-25" ,hour = "14:01", place = "Cancha de pasto principal", category = "Masculina", rules = "Amistoso", teamOnePoints = 5, teamTwoPoints = 2,time = "2022-04-25 14:01"),
  StructureDataMatch("UANDES", teamTwo = "", date ="2022-06-25" ,hour = "14:00", place = "Cancha de pasto secundaria", category = "Masculina", rules = "Se prohibe ...", teamOnePoints = -3, teamTwoPoints = -3,time = "2022-06-25 14:00"),
  StructureDataMatch("UANDES", teamTwo = "UCHILE",date ="2022-05-25" ,hour = "14:00", place = "Cancha de pasto secundaria", category = "Masculina", rules = "Se prohibe ...", teamOnePoints = -2, teamTwoPoints = -2,time = "2022-05-25 14:00"),
  StructureDataMatch("UANDES", teamTwo = "BRAZIL",date ="1999-04-25" ,hour = "14:00", place = "Cancha de pasto princiapl", category = "Masculina", rules = "Se prohibe ...", teamOnePoints = 7, teamTwoPoints = 0,time = "1999-04-25 14:00"),
  StructureDataMatch("UDD", teamTwo = "",date ="1000-04-25" ,hour = "14:00", place = "Cancha de pasto secundaria", category = "Masculina", rules = "Se prohibe ...", teamOnePoints = -1, teamTwoPoints = -1,time = "1000-04-25 14:00"),
  StructureDataMatch("UAI", teamTwo = "UC",date ="2020-04-25" ,hour = "12:00", place = "Cancha de pasto principal", category = "Masculina", rules = "Sin reglas establecidas", teamOnePoints = -1, teamTwoPoints = -1,time = "2020-04-25 12:00"),
  StructureDataMatch("UAI", teamTwo = "UNAB",date ="1001-04-25" ,hour = "14:00", place = "Cancha de pasto principal", category = "Masculina", rules = "Amistoso", teamOnePoints = -1, teamTwoPoints = -1,time = "1001-04-25 14:00"),
  StructureDataMatch("UAI", teamTwo = "UNAB",date ="2022-07-25" ,hour = "14:00", place = "Cancha de pasto principal", category = "Masculina", rules = "Sin reglas establecidas", teamOnePoints = -1, teamTwoPoints = -1,time = "2022-07-25 14:00"),
  StructureDataMatch("UAI", teamTwo = "",date ="2022-08-10" ,hour = "14:00", place = "Cancha de pasto principal", category = "Masculina", rules = "Sin reglas establecidas", teamOnePoints = -1, teamTwoPoints = -1,time = "2022-08-10 14:00"),
  StructureDataMatch("UDD", teamTwo = "UANDES",date ="2022-04-25" ,hour = "14:00", place = "Cancha de pasto principal", category = "Masculina", rules = "Amistoso", teamOnePoints = -1, teamTwoPoints = -1,time = "2022-04-25 14:00")
)

fun appenderMatches(arr: Array<DataMatch>, element: DataMatch): Array<DataMatch> {
  val mutableArray = arr.toMutableList()
  mutableArray.add(element)
  return mutableArray.toTypedArray()
}

class MainActivity : AppCompatActivity() {

  private var _binding: ActivityMainBinding? = null
  private val binding get() = _binding
  private lateinit var navigationController: NavController

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    _binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding?.root)

    // Buscamos el navigation view
    val navigationView: BottomNavigationView = binding?.navigationView!!

    // buscamos el nav host
    navigationController = findNavController(R.id.nav_host_fragment_activity_main)
//    NavigationUI.setupActionBarWithNavController(this, navigationController)

    // seteamos las navegaciones que van en la barra
    val appBarConfiguration = AppBarConfiguration(
      setOf(R.id.nav_graph)
    )
    // seteamos el action bar con el navigation controller y la confiuración creada
    //setupActionBarWithNavController(navigationController, appBarConfiguration)
    // le decimos al navigation view cual va a ser su controlador
    navigationView.setupWithNavController(navigationController)

    if (!isLoggedIn) {
      navigationController.navigate(R.id.login)
      navigationView.visibility = View.GONE
    }
  }

  override fun onSupportNavigateUp(): Boolean {
    return NavigationUI.navigateUp(navigationController, null)
  }
}
