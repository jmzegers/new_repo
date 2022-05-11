package cl.uandes.pichangapp.ui.views.Utility

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation
import cl.uandes.pichangapp.databinding.RatePichangasBinding
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import cl.uandes.pichangapp.data.models.DataMatch
import cl.uandes.pichangapp.ui.views.ItemAdapter
import cl.uandes.pichangapp.ui.views.Matchesss
import cl.uandes.pichangapp.ui.views.contextSituation
import cl.uandes.pichangapp.R
import cl.uandes.pichangapp.ui.views.User.User_pichangas
import cl.uandes.pichangapp.ui.viewmodel.Utility.RatePichangasViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class Rate_pichangas : Fragment(), ItemAdapter.ActionListener  {
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
    private var _binding: RatePichangasBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = RatePichangasBinding.inflate(inflater, container, false)
        detailsItemAdapter = ItemAdapter(allFighters.toMutableList(), this) //new
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        contextSituation = "rate"

        val btn1 = view.findViewById<Button>(R.id.from_rate_to_history);
        btn1.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.history_pichangas)
        }

        val btn2 = view.findViewById<Button>(R.id.from_rate_to_userpichangas);
        btn2.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.user_pichangas)
        }
//
//        val btn3 = view.findViewById<Button>(R.id.from_misPichangas_to_buscarPichangas);
//        btn3.setOnClickListener {
//            Navigation.findNavController(it).navigate(R.id.pichanga_search)
//        }
        //recyclerview
        val fighterListView = binding.fighterListView
        fighterListView.layoutManager = LinearLayoutManager(context)
        fighterListView.adapter = detailsItemAdapter

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment User_pichangas.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            User_pichangas().apply {
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

        /* -> El siguiente codigo es para que al apretar dos items, no se caiga la app */
        val now = System.currentTimeMillis()
        if (now - mLastClickTime < CLICK_TIME_INTERVAL) {
            return;
        }

        mLastClickTime = now;
        /* -> hasta aqui <- */

        // aqui mandamos el fullName del fighter seleccionado
        val bundle = bundleOf("fighterName" to fighter.teamOne.toString())
        // llamamos la accion para navegar y el argumento entregado
        findNavController().navigate(R.id.action_rate_pichangas_to_selected_item, bundle)
    }
}