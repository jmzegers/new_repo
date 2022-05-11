package cl.uandes.pichangapp.ui.views.User

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import cl.uandes.pichangapp.databinding.HomepageBinding
import cl.uandes.pichangapp.data.models.DataMatch
import cl.uandes.pichangapp.ui.views.ItemAdapter
import cl.uandes.pichangapp.ui.views.Matchesss
import cl.uandes.pichangapp.ui.views.elementId
import cl.uandes.pichangapp.ui.views.contextSituation
import cl.uandes.pichangapp.R
import cl.uandes.pichangapp.ui.viewmodel.User.HomepageViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Homepage.newInstance] factory method to
 * create an instance of this fragment.
 */
class Homepage : Fragment(), ItemAdapter.ActionListener   {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    private lateinit var detailsItemAdapter: ItemAdapter  // new
    private val allFighters = Matchesss // FakeData Direction //new    private var mLastClickTime = System.currentTimeMillis() //new
    private var mLastClickTime = System.currentTimeMillis() //new
    private val CLICK_TIME_INTERVAL: Long = 300     //new
    private var _binding: HomepageBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = HomepageBinding.inflate(inflater, container, false)
        detailsItemAdapter = ItemAdapter(allFighters.toMutableList(), this) //new
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        contextSituation = "homepage"
        val btn1 = view.findViewById<Button>(R.id.from_homepage_to_login);
        btn1.setOnClickListener {
            elementId = ""
            val bottomNavigationView = activity?.findViewById<View>(R.id.navigation_view)
            bottomNavigationView?.visibility = View.GONE
            Navigation.findNavController(it).navigate(R.id.login)
        }

        val btn2 = view.findViewById<Button>(R.id.from_homepage_to_perfil);
        btn2.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.profile)
        }

        val fighterListView = binding.fighterListView
        fighterListView.layoutManager = LinearLayoutManager(context)
        fighterListView.adapter = detailsItemAdapter
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Homepage().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


    override fun goToFighterDetails(fighter: DataMatch) {
        /* ir al Fragment Fighter Details */
        // usando safe args...

        /* -> El siguiente codigo es para que al apretar dos items, no se cai           ga la app */
        val now = System.currentTimeMillis()
        if (now - mLastClickTime < CLICK_TIME_INTERVAL) {
            return;
        }

        mLastClickTime = now;
        /* -> hasta aqui <- */

        // aqui mandamos el fullName del fighter seleccionado
        val bundle = bundleOf("fighterName" to fighter.teamOne.toString())
        // llamamos la accion para navegar y el argumento entregado
        findNavController().navigate(R.id.action_homepage_to_selected_item, bundle)
    }
}