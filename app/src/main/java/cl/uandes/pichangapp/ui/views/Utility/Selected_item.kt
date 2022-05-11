package cl.uandes.pichangapp.ui.views.Utility

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import cl.uandes.pichangapp.databinding.SelectedItemBinding
import cl.uandes.pichangapp.data.models.DataMatch
import cl.uandes.pichangapp.data.models.Matches
import cl.uandes.pichangapp.ui.views.DetailsItemAdapter
import cl.uandes.pichangapp.ui.views.contextSituation
import cl.uandes.pichangapp.R
import cl.uandes.pichangapp.ui.views.User.Homepage

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Homepage.newInstance] factory method to
 * create an instance of this fragment.
 */
class Selected : Fragment(), DetailsItemAdapter.ActionListener   {
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

    private lateinit var detailsItemAdapter: DetailsItemAdapter  // new
    private val allFighters = Matches.MyNextMatches() // FakeData Direction //new
    private var mLastClickTime = System.currentTimeMillis() //new
    private val CLICK_TIME_INTERVAL: Long = 300     //new
    private var _binding: SelectedItemBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = SelectedItemBinding.inflate(inflater, container, false)
        detailsItemAdapter = DetailsItemAdapter(allFighters.toMutableList(), this) //new
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        contextSituation = "homepage"


        val fighterListView = binding.details
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
        Toast.makeText(context, "hola! hiciste click en el ${fighter.teamOne}", Toast.LENGTH_LONG).show()
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
        findNavController().navigate(R.id.selected_item, bundle)
    }
}